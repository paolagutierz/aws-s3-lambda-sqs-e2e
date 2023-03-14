package co.com.bancolombia.certification.aid.exceptions;

import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonException extends JsonParseException {
    private static final long serialVersionUID = 48323363456853567L;
    private static final Logger LOGGER = LogManager.getLogger("JSONException");

    public JsonException(String msg, Throwable e) {
        super(msg, e);
        LOGGER.info(e.getMessage(), e);
    }
}
