package co.com.bancolombia.certification.aid.documents.utils;

import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import com.google.gson.JsonObject;
import org.json.JSONObject;

public class ValidationsRINT {
    public static void validateDynamoDBResult(JsonObject jsonResultComplete, JsonObject jsonResultEntities) {
        String pathValues = "src/test/resources/files/json_results/reintentos.json";
        String pathKeys = "src/test/resources/files/json_results/rint_keys.json";
        String documentType = ExecutionMemory.getDocumentType();

        JsonObject jsonKeys = DataUtils.jsonExtractor(pathKeys);
        JSONObject jsonExpectedKeys = new JSONObject(jsonKeys.get("reintentos").toString());
        JsonObject jsonExpectedEntityValues = DataUtils.jsonExtractor(pathValues);

        JSONObject expectedObjectEntities = new JSONObject(jsonExpectedEntityValues.get(documentType).toString());
        JSONObject obtainedObjectComplete = new JSONObject(jsonResultComplete.toString());
        JSONObject obtainedObjectEntities = new JSONObject(jsonResultEntities.toString());

        if (ControlValidations.checkStatusAndId(obtainedObjectComplete)){
            ControlValidations.checkExpectedEntities(obtainedObjectEntities, expectedObjectEntities, jsonExpectedKeys);
        }
    }
}
