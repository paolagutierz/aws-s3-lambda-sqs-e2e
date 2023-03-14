package co.com.bancolombia.certification.aid.exceptions;

public class ErrorRestServiceException extends RuntimeException {

    public ErrorRestServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}