package co.com.bancolombia.certification.aid.interactions;

import co.com.bancolombia.certification.aid.exceptions.GeneralException;
import co.com.bancolombia.certification.aid.exceptions.InvalidResponseException;
import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import co.com.bancolombia.certification.aid.questions.ValidateResultStatus;
import co.com.bancolombia.certification.aid.utils.RequestConfigurations;
import co.com.bancolombia.certification.aid.utils.RestService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.annotations.Step;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class ConsultResult implements Interaction {

    private static final Logger LOGGER = Logger.getLogger(ConsultResult.class.getName());

    public ConsultResult() {
        //Do Nothing
    }

    @Step("{0} consult the status of the processing with the TransacionID")
    @Override
    public <T extends Actor> void performAs(T actor) {
        RequestConfigurations.configureGetResultRequest();
        RestService service = new RestService();
        boolean checkControl = false;
        long currentTime;
        int attempt = 0;
        long time = System.currentTimeMillis();
        do {
            service.post(ExecutionMemory.getApiEndpoint(), ExecutionMemory.getBodyString(), ExecutionMemory.getHeaders());
            currentTime = System.currentTimeMillis();
            attempt++;
            LOGGER.info("### Consult result validation check: [attempt:" + attempt + "] " +
                    "\n[response:" + ExecutionMemory.getResponse() + "]" +
                    "\n[time:" + (currentTime - time) + "]");
            if (ExecutionMemory.getResponse().contains("Fragmento procesado")) {
                checkControl = true;
            } else {
                validateStatusInProgress();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new GeneralException("Error waiting for one sec on ConsultResult -> performAs [InterruptedException]", e);
                }
            }
        } while (!checkControl);
        ExecutionMemory.setTimeElapsed((int) (currentTime - time));
    }

    private void validateStatusInProgress() {
        OnStage.theActorInTheSpotlight()
                .should(seeThat(ValidateResultStatus.inProcess(), is("{\"processStatus\":\"201\",\"processStatusDescription\":\"EN PROCESO\"}"))
                        .orComplainWith(InvalidResponseException.class, "The consult status result is not what is expected."));
    }

    public static ConsultResult processingStatus() {
        return Tasks.instrumented(ConsultResult.class);
    }
}
