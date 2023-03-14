package co.com.bancolombia.certification.aid.documents.stepdefinitions;

import co.com.bancolombia.certification.aid.documents.exceptions.AssertionException;
import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import co.com.bancolombia.certification.aid.documents.questions.ValidateResult;
import co.com.bancolombia.certification.aid.documents.tasks.CheckResult;
import co.com.bancolombia.certification.aid.documents.tasks.SendDocument;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import java.util.logging.Logger;
import static org.hamcrest.Matchers.containsString;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class ProcessDocumentStepDefinitions {

    private static final Logger LOGGER = Logger.getLogger(ProcessDocumentStepDefinitions.class.getName());
    private static final String USER = "Pepito";

    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(USER);
        ExecutionMemory.setWorkflowArgType(System.getProperty("workflow") + " reintentos"); //declaracion_renta carta_laboral
        ExecutionMemory.setRunTest(false);
    }

    @When("^I send a document type (.*) to process with (.*) model$")
    public void iSendADocumentTypeToProcessWithModel(String documentType, String workflowType) {
        LOGGER.info(String.format("--> STEP 1: SEND DOCUMENT[%s] TO BE PROCESSED BY WORKFLOW[%S]", documentType, workflowType));
        OnStage.theActorInTheSpotlight().attemptsTo(
                SendDocument.toS3Bucket(documentType, workflowType)
        );
    }

    @When("^I ask for the result of processing$")
    public void iAskForTheResultOfProcessing() {
        LOGGER.info("--> STEP 2: ASK FOR RESULTS IN DYNAMODB");
        OnStage.theActorInTheSpotlight().attemptsTo(
                CheckResult.ofProccessingInDynamoDB()
        );
    }

    @Then("^I should see the extracted information$")
    public void iShouldSeeTheExtractedInformation() {
        LOGGER.info("--> STEP 3: VALIDATE RESULTS");
        OnStage.theActorInTheSpotlight()
                .should(seeThat(ValidateResult.ofTheDocument(), containsString("SUCCESS"))
                        .orComplainWith(AssertionException.class, "The processed result don't match with expected result."));
    }

}
