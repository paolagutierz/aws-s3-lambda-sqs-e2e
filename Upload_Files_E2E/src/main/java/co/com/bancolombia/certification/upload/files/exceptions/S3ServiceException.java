package co.com.bancolombia.certification.upload.files.exceptions;

public class S3ServiceException extends RuntimeException {
    public S3ServiceException(String message, Exception cause) {
        super(message, cause);
    }

}
