package co.com.bancolombia.certification.aid.documents.exceptions;

import com.amazonaws.services.s3.model.AmazonS3Exception;

public class S3ServiceException extends AmazonS3Exception {

    public S3ServiceException(String message, Exception cause) {
        super(message, cause);
    }

}
