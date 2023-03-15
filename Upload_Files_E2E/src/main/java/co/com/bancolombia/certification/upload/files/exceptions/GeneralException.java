package co.com.bancolombia.certification.upload.files.exceptions;

public class GeneralException extends Exception {

    public GeneralException(String mensaje) {
        super(mensaje);
    }

    public GeneralException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
