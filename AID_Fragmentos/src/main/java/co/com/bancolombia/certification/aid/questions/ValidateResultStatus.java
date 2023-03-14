package co.com.bancolombia.certification.aid.questions;

import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;

public class ValidateResultStatus implements Question<String> {

    public ValidateResultStatus(){
        //Do nothing
    }

    @Step("{0} check result status IN PROCESS from the response")
    @Override
    public String answeredBy(Actor actor) {
        String check = "fail";
        if (ExecutionMemory.getResponse().contains("processStatus")){
            check = ExecutionMemory.getResponse();
        }
        return check;
    }

    public static ValidateResultStatus inProcess(){ return new ValidateResultStatus();}
}
