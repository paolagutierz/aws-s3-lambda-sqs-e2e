package co.com.bancolombia.certification.aid.documents.utils;

import co.com.bancolombia.certification.aid.documents.questions.ValidateResult;
import com.amazonaws.services.lambda.model.InvokeResult;


public class ControlValidator {

    public static String processFunctionalResponse(InvokeResult lambdaResult, String testCase){
        ValidateResult validateResult = new ValidateResult();
        String answer = DataUtils.payloadExtractor(lambdaResult);
        return question.isValidFunctionalResponse(answer, testCase);
    }


}
