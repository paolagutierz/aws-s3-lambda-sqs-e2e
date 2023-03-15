package co.com.bancolombia.certification.aid.documents.questions;

import co.com.bancolombia.certification.aid.documents.utils.aws.SQSExecutor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;

public class ValidateResult implements Question<String> {

    @Override
    @Step("{0} validate result of the processed document")
    public String answeredBy(Actor actor) {
        return SQSExecutor.readFirstMessageFromQueue();
    }

    public static ValidateResult firstSQSMessage() {
        return new ValidateResult();
    }
}
