package co.com.bancolombia.certification.aid.questions;

import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;
import java.util.logging.Logger;

public class ValidateSendStatus implements Question<String> {

    private static final Logger LOGGER = Logger.getLogger(ValidateSendStatus.class.getName());

    public ValidateSendStatus(){
        //Do nothing.
    }

    @Step("{0} check the status of the submitted fragment")
    @Override
    public String answeredBy(Actor actor) {
        String check = "fail";
        if (ExecutionMemory.getResponse().contains("Ok")){
            check = ExecutionMemory.getResponse();
        }
        LOGGER.info("### SEND FRAGMENT VALIDATION ### " +
                "\n[expected >>\"{\"message\":\"Ok\"}\"<<] " +
                "\n[obtained >>" + ExecutionMemory.getResponse() + "<<]");
        return check;
    }

    public static ValidateSendStatus sendingFragment(){
        return new ValidateSendStatus();
    }
}
