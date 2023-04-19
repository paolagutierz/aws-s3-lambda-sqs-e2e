package co.com.bancolombia.certification.upload.files.questions;

import co.com.bancolombia.certification.upload.files.utils.aws.SQSExecutor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;

public class ValidateResultSqs implements Question<String> {

    @Override
    @Step("{0} validate result of the processed image")
    public String answeredBy(Actor actor) {
        return SQSExecutor.readFirstMessageFromQueue();
    }

    public static ValidateResultSqs firstSQSMessage() {
        return new ValidateResultSqs();
    }
}
