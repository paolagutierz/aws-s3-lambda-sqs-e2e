package co.com.bancolombia.certification.aid.exceptions;

public class InvalidResponseException extends AssertionError {

    public InvalidResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
