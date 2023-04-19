package co.com.bancolombia.certification.upload.files.exceptions;

public class SqsServiceException  extends RuntimeException {

    public SqsServiceException(String message, Exception cause) {
        super(message, cause);
}

}
