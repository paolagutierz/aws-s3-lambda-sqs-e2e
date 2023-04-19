package co.com.bancolombia.certification.upload.files.utils.aws;

import co.com.bancolombia.certification.upload.files.exceptions.SqsServiceException;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.regions.Region;

public class AmazonSQSClient {

    public static SqsClient getSQSClient() {
        try {
            SqsClient sqsClient = SqsClient.builder()
                    .region(Region.US_EAST_1)
                    .build();
            return sqsClient;
        } catch (SdkException e) {
            throw new SqsServiceException("Client error [getSQSClient]: " + e.getMessage(),e);
        }
    }
}