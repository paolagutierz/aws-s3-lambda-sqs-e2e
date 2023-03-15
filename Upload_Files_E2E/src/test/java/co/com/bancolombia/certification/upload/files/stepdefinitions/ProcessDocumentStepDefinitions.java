package co.com.bancolombia.certification.upload.files.stepdefinitions;

import co.com.bancolombia.certification.upload.files.exceptions.AssertionException;
import co.com.bancolombia.certification.upload.files.questions.ValidateResult;
import co.com.bancolombia.certification.upload.files.tasks.SendFile;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.logging.Logger;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

public class ProcessDocumentStepDefinitions {

    private static final Logger LOGGER = Logger.getLogger(ProcessDocumentStepDefinitions.class.getName());
    private static final String USER = "lucy";

    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(USER);
    }

    @When("^I send a pdf file (.*)")
    public void iSendAPdfFile(String file) {
        LOGGER.info(String.format("--> STEP 1: SEND DOCUMENT[%s] TO S3", file));
        OnStage.theActorInTheSpotlight().attemptsTo(
                SendFile.toS3Bucket(file)
        );
    }

    @Then("^I should see the message in SQS contains the file name (.*)$")
    public void iShouldSeeTheMessageInSQSContainsTheFileName(String fileName) {
        LOGGER.info("--> STEP 2: VALIDATE RESULT IN SQS");
        OnStage.theActorInTheSpotlight()
                .should(seeThat(ValidateResult.firstSQSMessage(), containsString(fileName))
                        .orComplainWith(AssertionException.class, "The processed result don't match with expected result."));
    }
}
