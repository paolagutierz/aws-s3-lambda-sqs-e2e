package co.com.bancolombia.certification.aid.documents.exceptions;

public class AssertionException extends AssertionError {

    public AssertionException(String mensaje) {
        super(mensaje);
    }

    public AssertionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
