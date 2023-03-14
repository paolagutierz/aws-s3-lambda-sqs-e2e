package co.com.bancolombia.certification.aid.documents.utils;

import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import com.google.gson.JsonObject;
import org.json.JSONObject;

public class ValidationsDR {

    public static void validateDynamoDBResult(JsonObject jsonResultComplete, JsonObject jsonResultEntities) {
        String pathValues = "src/test/resources/files/json_results/declaracion_renta.json";
        String pathKeys = "src/test/resources/files/json_results/dr_keys.json";
        String documentType = ExecutionMemory.getDocumentType();
        JSONObject jsonExpectedKeys;

        JsonObject jsonKeys = DataUtils.jsonExtractor(pathKeys);

        if (!documentType.contains("flujo-fallido")) {
            jsonExpectedKeys = new JSONObject(jsonKeys.get(documentType.contains("210") ? documentType.split("-")[0] + "-210" : "110").toString());
        }else{
            jsonExpectedKeys = new JSONObject(jsonKeys.get("ff").toString());
        }

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
