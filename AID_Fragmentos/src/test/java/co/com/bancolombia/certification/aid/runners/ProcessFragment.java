package co.com.bancolombia.certification.aid.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/process_fragments.feature",
        glue = "co.com.bancolombia.certification.aid.stepdefinitions",
        snippets = SnippetType.CAMELCASE)
public class ProcessFragment {
}
