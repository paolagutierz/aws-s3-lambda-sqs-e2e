package co.com.bancolombia.certification.aid.documents.utils;

import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import com.google.gson.JsonObject;
import org.json.JSONObject;

public class ValidationsCL {

    public static void validateDynamoDBResult(JsonObject jsonResultComplete, JsonObject jsonResultEntities) {
        String pathValues = "src/test/resources/files/json_results/carta_laboral.json";
        String pathKeys = "src/test/resources/files/json_results/cl_keys.json";
        String documentType = ExecutionMemory.getDocumentType();
        String KeysToGet = !documentType.contains("flujo-fallido") ? "v1" : "ff";

        JsonObject jsonKeys = DataUtils.jsonExtractor(pathKeys);
        JSONObject jsonExpectedKeys = new JSONObject(jsonKeys.get(KeysToGet).toString());
        JsonObject jsonExpectedEntityValues = DataUtils.jsonExtractor(pathValues);
        jsonExpectedEntityValues = DataUtils.putValue(jsonExpectedEntityValues,"<environment>", System.getProperty("environment"));
        jsonExpectedEntityValues = DataUtils.putValue(jsonExpectedEntityValues,"<FileName>", ExecutionMemory.getFileName());

        JSONObject expectedObjectEntities = new JSONObject(jsonExpectedEntityValues.get(documentType).toString());
        JSONObject obtainedObjectComplete = new JSONObject(jsonResultComplete.toString());
        JSONObject obtainedObjectEntities = new JSONObject(jsonResultEntities.toString());

        if (ControlValidations.checkStatusAndId(obtainedObjectComplete)){
            ControlValidations.checkExpectedEntities(obtainedObjectEntities, expectedObjectEntities, jsonExpectedKeys);
        }
    }
}
