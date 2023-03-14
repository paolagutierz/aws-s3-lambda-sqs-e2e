package co.com.bancolombia.certification.aid.tasks;

import co.com.bancolombia.certification.aid.exceptions.InvalidResponseException;
import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import co.com.bancolombia.certification.aid.questions.ValidateSendStatus;
import co.com.bancolombia.certification.aid.utils.RequestConfigurations;
import co.com.bancolombia.certification.aid.utils.RestService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.annotations.Step;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class SendFragment implements Task {

    private String model;
    private String type;

    public SendFragment(String type, String model) {
        this.model = model;
        this.type = type;
    }

    @Step("{0} submit a text fragment to be processed")
    @Override
    public <T extends Actor> void performAs(T actor) {
        RestService service = new RestService();
        RequestConfigurations.configureSendFragmentRequest(type, model);
        service.post(ExecutionMemory.getApiEndpoint(), ExecutionMemory.getBodyString(), ExecutionMemory.getHeaders());
        validateResponse();
    }

    private void validateResponse() {
        OnStage.theActorInTheSpotlight()
                .should(seeThat(ValidateSendStatus.sendingFragment(), is("{\"message\":\"Ok\"}"))
                        .orComplainWith(InvalidResponseException.class, "The service for send fragment don't response."));
    }

    public static SendFragment toProcess(String type, String model) {
        return Tasks.instrumented(SendFragment.class, type, model);
    }
}
