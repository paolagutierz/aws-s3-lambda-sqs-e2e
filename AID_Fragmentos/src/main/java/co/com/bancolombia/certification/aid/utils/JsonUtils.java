package co.com.bancolombia.certification.aid.utils;

import co.com.bancolombia.certification.aid.exceptions.GeneralException;
import co.com.bancolombia.certification.aid.exceptions.JsonException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private JsonUtils(){
        //Do nothing.
    }

    public static JsonObject jsonExtractor(String path) {
        JsonParser jsonParser = new JsonParser();
        Object archivoJson;
        JsonObject objArchivoJson = null;
        try (FileReader reader = new FileReader(path)) {
            archivoJson = jsonParser.parse(reader);
            objArchivoJson = (JsonObject) archivoJson;
        } catch (JsonParseException e) {
            throw new JsonException("Error on JsonUtils -> jsonExtractor [JsonException]", e);
        } catch (FileNotFoundException e) {
            throw new GeneralException("Error on JsonUtils -> jsonExtractor [FileNotFoundException]", e);
        } catch (IOException e) {
            throw new GeneralException("Error on JsonUtils -> jsonExtractor [IOException]", e);
        }
        return objArchivoJson;
    }

    public static JsonObject putValue(JsonObject jsonObject, String target, String value) {
        JsonParser parser = new JsonParser();
        String objJsonAux = jsonObject.toString().replace(target, value);
        return (JsonObject) parser.parse(objJsonAux);
    }
}
