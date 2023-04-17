package co.com.bancolombia.certification.upload.files.stepdefinitions;

import co.com.bancolombia.certification.upload.files.exceptions.AssertionException;
import co.com.bancolombia.certification.upload.files.models.ExecutionMemory;
import co.com.bancolombia.certification.upload.files.questions.ValidateResult;
import co.com.bancolombia.certification.upload.files.tasks.DownloadFile;
import co.com.bancolombia.certification.upload.files.tasks.SendFile;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.logging.Logger;

import static co.com.bancolombia.certification.upload.files.tasks.DownloadFile.fromS3Bucket;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class ProcessDocumentStepDefinitions {

    private static final Logger LOGGER = Logger.getLogger(ProcessDocumentStepDefinitions.class.getName());
    private static final String USER = "lucy";

    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(USER);
    }

    @When("^I upload the image (.*) to s3 bucket$")
    public void iSendAnImage(String file) {
        LOGGER.info(String.format("--> STEP 1: SEND IMAGE [%s] TO S3 BUCKET", file));
        OnStage.theActorInTheSpotlight().attemptsTo(
                SendFile.toS3Bucket(file));
    }


    @And("^Download the resize image from the destination bucket$")
    public void downloadResizeImage(){
        String filename= ExecutionMemory.getFileName();
        LOGGER.info(String.format("--> STEP 2: DOWNLOAD IMAGE [%s] FROM DESTINATION S3 BUCKET", filename));
        OnStage.theActorInTheSpotlight().attemptsTo(
                DownloadFile.fromS3Bucket());
    }

    @Then("^I should see that the downloaded image has a smaller size than the uploaded image$")
    public void iShouldSeeTheResizeImage() {
        LOGGER.info("--> STEP 3: VALIDATE IMAGE WAS RESIZE");
        OnStage.theActorInTheSpotlight()
                .should(seeThat(ValidateResult.imageWasResize(), is(true))
                        .orComplainWith(AssertionException.class, "The processed result don't match with expected result."));
    }
}
