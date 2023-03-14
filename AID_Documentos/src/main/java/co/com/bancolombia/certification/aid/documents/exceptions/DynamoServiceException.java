package co.com.bancolombia.certification.aid.documents.exceptions;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;

public class DynamoServiceException extends AmazonDynamoDBException {

    public DynamoServiceException(String message) {
        super(message);
    }

}
