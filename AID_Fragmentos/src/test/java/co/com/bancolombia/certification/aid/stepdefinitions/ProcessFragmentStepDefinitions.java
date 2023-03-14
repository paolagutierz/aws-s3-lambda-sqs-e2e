package co.com.bancolombia.certification.aid.stepdefinitions;

import co.com.bancolombia.certification.aid.exceptions.InvalidResponseException;
import co.com.bancolombia.certification.aid.interactions.ConsultResult;
import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import co.com.bancolombia.certification.aid.questions.CheckApiGatewayResponse;
import co.com.bancolombia.certification.aid.questions.CheckTimeElapsed;
import co.com.bancolombia.certification.aid.questions.ValidateFragmentProcessing;
import co.com.bancolombia.certification.aid.tasks.SendFragment;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.logging.Logger;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class ProcessFragmentStepDefinitions {

    private static final Logger LOGGER = Logger.getLogger(ProcessFragmentStepDefinitions.class.getName());
    private static final String PEPE = "Pepe";

    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(PEPE);
        ExecutionMemory.setTimeExpected(Integer.parseInt(System.getProperty("timeExpected")));
    }

    @When("^I send a fragment type (.*) to process with (.*) model$")
    public void iSendAFragmentTypeToProcessWithModel(String type, String model) {
        LOGGER.info("--> STEP 1: SEND FRAGMENT");
        OnStage.theActorInTheSpotlight().attemptsTo(SendFragment.toProcess(type, model));
    }

    @When("^I ask for the result of processing$")
    public void iAskForTheResultOfProcessing() {
        LOGGER.info("--> STEP 2: GET RESULT");
        OnStage.theActorInTheSpotlight().attemptsTo(ConsultResult.processingStatus());
    }

    @Then("^I should see the extracted information$")
    public void iShouldSeeTheExtractedInformation() {
        LOGGER.info("--> STEP 3: VALIDATE RESULT AND TIME OF PROCESSING");
        OnStage.theActorInTheSpotlight()
                .should(seeThat(CheckApiGatewayResponse.isSuccess(), is("success"))
                        .orComplainWith(InvalidResponseException.class, "The service don't response during the test."));
        OnStage.theActorInTheSpotlight()
                .should(seeThat(ValidateFragmentProcessing.result(), is("success"))
                        .orComplainWith(InvalidResponseException.class, "The result processing don't match with expected result."));
        OnStage.theActorInTheSpotlight()
                .should(seeThat(CheckTimeElapsed.processingInMilisecs(), lessThan(ExecutionMemory.getTimeExpected()))
                        .orComplainWith(InvalidResponseException.class, "The service don't response during the expected time."));
    }
}
