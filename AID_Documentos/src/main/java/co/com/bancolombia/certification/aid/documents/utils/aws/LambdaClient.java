package co.com.bancolombia.certification.aid.documents.utils.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;

public class LambdaClient {

            public static AWSLambda getLambdaClient(){
            try {
                AWSLambda lambdaClient = AWSLambdaClientBuilder.standard()
                        .withCredentials(new SystemPropertiesCredentialsProvider())
                        .withRegion(Regions.US_EAST_1)
                        .build();
                return  lambdaClient;
            } catch (AmazonServiceException e){
                throw new AmazonServiceException("Client error [getLambdaClient]: " + e);
            }
        }

    }


