package co.com.bancolombia.certification.upload.files.exceptions;

import com.amazonaws.services.s3.model.AmazonS3Exception;

public class S3ServiceException extends AmazonS3Exception {

    public S3ServiceException(String message, Exception cause) {
        super(message, cause);
    }

}
