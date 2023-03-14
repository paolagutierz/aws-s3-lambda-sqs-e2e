package co.com.bancolombia.certification.aid.utils;

import co.com.bancolombia.certification.aid.exceptions.JsonException;
import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class RequestConfigurations {

    public static void configureSendFragmentRequest(String type, String model) {
        ExecutionMemory.setApiEndpoint(System.getProperty("endpoint") + "/process");
        setHeaders();
        setSendFragmentBody(type, model);
    }

    public static void configureGetResultRequest() {
        ExecutionMemory.setApiEndpoint(System.getProperty("endpoint") + "/result");
        if (ExecutionMemory.getHeaders().isEmpty()) {
            setHeaders();
        }
        setGetResultBody();
    }

    private static void setHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("request-time", DataUtils.getTimeStamp());
        headers.put("message-id", DataUtils.getUUIDv4().replace("-", "").substring(20));
        headers.put("application-id", "AW_FUNCTIONAL_E2E_TEST" + DataUtils.getIdString());
        ExecutionMemory.setHeaders(headers);
    }

    private static void setSendFragmentBody(String type, String model) {
        JSONObject dataObject = new JSONObject();
        JSONObject requestJsonBody = new JSONObject();
        String transactionId = DataUtils.getUUIDv4();
        try {
            JsonObject myDataSet = JsonUtils.jsonExtractor("src/test/resources/jsons/fragments_results.json");
            dataObject.put("fragment", DataUtils.cleanSpecialCaracters(myDataSet.getAsJsonObject("fragments").get(type.toUpperCase()).getAsString()));
            ExecutionMemory.setExpectedResponse(myDataSet.getAsJsonObject("results").get(type.toUpperCase()).getAsString());
        } catch (Exception e) {
            throw new JsonException("Error extract json fragments on RequestConfigurations -> setFragmentBody", e);
        }
        dataObject.put("analyticalModelID", model);
        dataObject.put("transactionId", transactionId);
        requestJsonBody.put("data", dataObject);
        ExecutionMemory.setFragmentId(type);
        ExecutionMemory.setTransactionId(transactionId);
        ExecutionMemory.setBodyString(requestJsonBody.toString());
    }

    private static void setGetResultBody() {
        JSONObject dataObject = new JSONObject();
        JSONObject requestJsonBody = new JSONObject();
        dataObject.put("transactionId", ExecutionMemory.getTransactionId());
        requestJsonBody.put("data", dataObject);
        ExecutionMemory.setBodyString(requestJsonBody.toString());
    }
}
