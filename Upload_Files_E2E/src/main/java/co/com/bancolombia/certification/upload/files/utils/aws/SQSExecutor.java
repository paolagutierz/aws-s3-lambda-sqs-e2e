package co.com.bancolombia.certification.upload.files.utils.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class SQSExecutor {

    final static String QUEUE_NAME = "S3-Lambda-SQS";

    public static String readFirstMessageFromQueue() {
        AmazonSQS amazonSQS = SQSClient.getSQSClient();
        String queueUrl = amazonSQS.getQueueUrl(QUEUE_NAME).getQueueUrl();
        List<Message> messages = amazonSQS.receiveMessage(queueUrl).getMessages();
        return messages.get(0).getBody();
    }
}
