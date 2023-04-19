package co.com.bancolombia.certification.upload.files.utils.aws;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class SQSExecutor {

    final static String QUEUE_NAME = "s3-destination-sqs";

    public static String readFirstMessageFromQueue() {
        GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
                .queueName(QUEUE_NAME)
                .build();
        SqsClient sqsClient = AmazonSQSClient.getSQSClient();
        String queueUrl = sqsClient.getQueueUrl(getQueueRequest).queueUrl();
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .build();
        List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();
        return messages.get(0).body();
    }
}