package co.com.bancolombia.certification.aid.questions;

import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;
import java.util.logging.Logger;

public class CheckTimeElapsed implements Question<Integer> {

    private static final Logger LOGGER = Logger.getLogger(CheckTimeElapsed.class.getName());

    public CheckTimeElapsed() {
        //Do nothing
    }

    @Step("{0} check the elapsed time during the process")
    @Override
    public Integer answeredBy(Actor actor) {
        LOGGER.info("### RESULT PROCESS TIME VALIDATION ### " +
                "\n[expected >>" + ExecutionMemory.getTimeExpected() + "<<]" +
                "\n[elapsed >>" + ExecutionMemory.getTimeElapsed() + "<<]");
        return ExecutionMemory.getTimeElapsed();
    }

    public static CheckTimeElapsed processingInMilisecs() {
        return new CheckTimeElapsed();
    }
}
