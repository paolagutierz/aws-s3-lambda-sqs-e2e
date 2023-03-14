package co.com.bancolombia.certification.aid.utils;

import co.com.bancolombia.certification.aid.exceptions.ErrorRestServiceException;
import co.com.bancolombia.certification.aid.exceptions.InvalidResponseException;
import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import co.com.bancolombia.certification.aid.questions.CheckApiGatewayResponse;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import net.serenitybdd.screenplay.actors.OnStage;
import java.util.Map;
import java.util.logging.Logger;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class RestService {

    private static final Logger LOGGER = Logger.getLogger(RestService.class.getName());
    private static final String password = "aid123";

    public RestService() {
        //Do nothing.
    }

    public void post(String endpoint, String body, Map<String, String> headers) {
        LOGGER.info("## REQUEST:" +
                "\n[url:" + endpoint + "]" +
                "\n[headers:" + headers.toString() + "]" +
                "\n[body:" + body + "]");
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(endpoint).headers(headers).body(body).asJson();
        } catch (UnirestException e) {
            throw new ErrorRestServiceException("Error send request on RestServices -> post [UnirestException]", e);
        }
        responseProcessing(endpoint, body, response);
    }

    public void responseProcessing(String endpoint, String body, HttpResponse<JsonNode> response) {
        int code = response.getStatus();
        String status = response.getStatusText();
        String responseStr = response.getBody().toString();
        if (code == 200) {
            LOGGER.info("## RESPONSE DONE ##\n## Status:[" + code + "-" + status + "]" +
                    "\n## Body:[" + responseStr + "]");
            ExecutionMemory.setResponse(responseStr);
            ExecutionMemory.setScenarioController("success");
        } else {
            LOGGER.warning("## RESPONSE FAIL ##\n## Status:[" + code + "-" + status + "]"+
                    "\n## Body:[" + responseStr + "]");
            ExecutionMemory.setScenarioController("fail");
            OnStage.theActorInTheSpotlight()
                    .should(seeThat(CheckApiGatewayResponse.isSuccess(), is("success"))
                            .orComplainWith(InvalidResponseException.class, "The service don't response during the test."));
        }
    }
}