package co.com.bancolombia.certification.aid.documents.questions;

import co.com.bancolombia.certification.aid.documents.models.ExecutionJson;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;

import static co.com.bancolombia.certification.aid.documents.models.ExecutionJson.*;

public class ValidateResult implements Question<String> {

    public ValidateResult(){
        //Do anything.
    }

    @Step("{0} validate result of the processed document")
    @Override
    public String answeredBy(Actor actor) {
        if (setBody()) return "SUCCESS - Pass";
        return getBody();
    }

    public static ValidateResult ofTheDocument(){
        return new ValidateResult();
    }
}
