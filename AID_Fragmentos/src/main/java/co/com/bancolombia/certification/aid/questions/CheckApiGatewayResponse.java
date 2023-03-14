package co.com.bancolombia.certification.aid.questions;

import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CheckApiGatewayResponse implements Question<String> {

    public CheckApiGatewayResponse() {
        //Do nothing.
    }

    @Override
    public String answeredBy(Actor actor) {
        return ExecutionMemory.getScenarioController();
    }

    public static CheckApiGatewayResponse isSuccess() {
        return new CheckApiGatewayResponse();
    }
}
