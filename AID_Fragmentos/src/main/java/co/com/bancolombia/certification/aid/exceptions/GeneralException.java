package co.com.bancolombia.certification.aid.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneralException extends RuntimeException {
    private static final long serialVersionUID = 4832336123978500145L;
    private static final Logger LOGGER = LogManager.getLogger("GeneralException");

    public GeneralException(String message, Exception e) {
        super(message, e);
        LOGGER.info(e.getMessage(), e);
    }
}
