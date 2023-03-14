package co.com.bancolombia.certification.aid.documents.utils.aws;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import com.opencsv.exceptions.CsvValidationException;
import tasks.CallServicesAws;
import utils.ControlValidator;

import java.io.IOException;


public class LambdaExecutor {
    final String lambdaName= "aw1188001-chatbot-sofy-"+System.getProperty("environment")+"-campaigns-ingester-lambda";
    final String bucketName= "aw1188001-chatbot-sofy-informacion-campaigns-"+System.getProperty("environment");
    final String arnBucket = "arn:aws:s3:::aw1188001-chatbot-sofy-informacion-campaigns-"+System.getProperty("environment");


    public String requestLambda(String typeTest) throws IOException, CsvValidationException {
        CallServicesAws.putFileInBucket(typeTest,bucketName);
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(lambdaName)
                .withPayload(CallServicesAws.createLambdaEvent(bucketName, arnBucket, typeTest))
                .withLogType("Tail");
        return responseLambda(invokeRequest, typeTest);
    }

    public String responseLambda(InvokeRequest invokeRequest, String controlTestType) {
        AWSLambda lambda = LambdaClient.getLambdaClient();
        InvokeResult invokeResult = null;
        try {
            invokeResult = lambda.invoke(invokeRequest);
        } catch (ServiceException e) {
            throw new ServiceException("Service error [invokeLambda]: " + e);
        }
        String answ = ControlValidator.processFunctionalResponse(invokeResult, controlTestType);
        System.out.printf("System.out.printf(\"response lambda\" + answ);" + answ);
        return answ;
    }


}