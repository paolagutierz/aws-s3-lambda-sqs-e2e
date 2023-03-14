package co.com.bancolombia.certification.aid.documents.utils;

import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import co.com.bancolombia.certification.aid.documents.utils.aws.S3Executor;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.logging.Logger;

public class ValidationsFO {

    private static final Logger LOGGER = Logger.getLogger(DynamoExecutor.class.getName());

    public static void validateDynamoDBResult(JsonObject jsonResultComplete, JsonObject jsonResultEntities) {
        String pathValues = "src/test/resources/files/json_results/full_ocr.json";
        String pathKeys = "src/test/resources/files/json_results/fo_keys.json";
        String documentType = ExecutionMemory.getDocumentType();
        String KeysToGet = !documentType.contains("flujo-fallido") ? "v1" : "ff";

        JsonObject jsonKeys = DataUtils.jsonExtractor(pathKeys);
        JSONObject jsonExpectedKeys = new JSONObject(jsonKeys.get(KeysToGet).toString());
        JsonObject jsonExpectedEntityValues = DataUtils.jsonExtractor(pathValues);

        String filenameWithoutExtension = ExecutionMemory.getFileName().substring(0, ExecutionMemory.getFileName().length()-4);
        jsonExpectedEntityValues = DataUtils.putValue(jsonExpectedEntityValues,"<environment>", System.getProperty("environment"));
        jsonExpectedEntityValues = DataUtils.putValue(jsonExpectedEntityValues,"<FileName>", ExecutionMemory.getFileName());
        jsonExpectedEntityValues = DataUtils.putValue(jsonExpectedEntityValues,"<FileNameWithoutExtension>", filenameWithoutExtension);

        JSONObject obtainedObjectComplete = new JSONObject(jsonResultComplete.toString());
        JSONObject obtainedObjectEntities = new JSONObject(jsonResultEntities.toString());

        JSONObject expectedObjectEntities;
        JSONArray obtainedOcr = new JSONArray();
        if (!documentType.contains("flujo-fallido")) {
            expectedObjectEntities = new JSONObject(jsonExpectedEntityValues.get("dynamoResult").toString());
            String[] s3path = obtainedObjectEntities.getString("full_ocr_json_path").split("/");
            String s3Key = String.join("/", Arrays.copyOfRange(s3path, 3, s3path.length));
            String fullOcrPath = S3Executor.getObjectS3(s3Key, filenameWithoutExtension + "_ocr.json");
            obtainedOcr = new JSONArray(DataUtils.jsonArrayExtractor(fullOcrPath).toString());
        } else {
            expectedObjectEntities = new JSONObject(jsonExpectedEntityValues.get(documentType).toString());
        }

        JSONObject expectedOcr = new JSONObject(jsonExpectedEntityValues.get(documentType).toString());

        if (ControlValidations.checkStatusAndId(obtainedObjectComplete)){
            ControlValidations.checkExpectedEntities(obtainedObjectEntities, expectedObjectEntities, jsonExpectedKeys);
            if (ExecutionMemory.getResultValidation().contains("SUCCESS")){
                ControlValidations.checkJsonFullOcrPages(expectedOcr, obtainedOcr);
                if (ExecutionMemory.getResultValidation().contains("SUCCESS")) {
                    ControlValidations.checkJsonFullOcrText(expectedOcr, obtainedOcr);
                }
            }
        }
    }
}
