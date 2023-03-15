package co.com.bancolombia.certification.upload.files.utils.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class SQSClient {

    public static AmazonSQS getSQSClient() {
        try {
            AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                    .withCredentials(new SystemPropertiesCredentialsProvider())
                    .withRegion(Regions.US_EAST_1)
                    .build();
            return sqsClient;
        } catch (AmazonServiceException e) {
            throw new AmazonServiceException("Client error [getSQSClient]: " + e);
        }
    }
}
