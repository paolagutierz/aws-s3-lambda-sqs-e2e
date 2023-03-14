package co.com.bancolombia.certification.aid.questions;

import co.com.bancolombia.certification.aid.models.ExecutionMemory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ValidateFragmentProcessing implements Question<String> {

    private static final Logger LOGGER = Logger.getLogger(ValidateFragmentProcessing.class.getName());

    public ValidateFragmentProcessing() {
        //Do nothing
    }

    @Step("{0} check the result of processing")
    @Override
    public String answeredBy(Actor actor) {
        String check = "success";
        String resultObtained = ExecutionMemory.getResponse();
        List<String> tokensExpected = Arrays.asList("cargo", "identificacion", "nombre", "tipo_identificacion",
                "processStatus","200","processStatusDescription","Fragmento procesado", "interpretacion_desembolso");
        List<String> resultExpected = Arrays.asList(ExecutionMemory.getExpectedResponse().split("~"));
        for (int i = 0; i < tokensExpected.size(); i++) {
            if (!resultObtained.contains(tokensExpected.get(i))) {
                check = "Fail, the result not contain the token: " + tokensExpected.get(i);
            }
        }
        int iterator = 0;
        while (("success").equals(check) && iterator < resultExpected.size()) {
            if (!resultObtained.toLowerCase().contains(resultExpected.get(iterator).toLowerCase())) {
                check = "Fail, the result not contain the item: " + resultExpected.get(iterator);
            }
            iterator++;
        }
        LOGGER.info("### RESULT PROCESS FRAGMENT VALIDATION ### " +
                "\n[expected >>" + resultExpected.toString() + "<<]" +
                "\n[obtained >>" + ExecutionMemory.getResponse() + "<<]" +
                "\n[RESULT VALIDATION >>[" + check + "]<<");
        return check;
    }

    public static ValidateFragmentProcessing result() {
        return new ValidateFragmentProcessing();
    }
}
