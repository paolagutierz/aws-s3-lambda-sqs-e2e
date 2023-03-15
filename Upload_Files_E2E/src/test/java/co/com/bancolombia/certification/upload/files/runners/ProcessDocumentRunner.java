package co.com.bancolombia.certification.upload.files.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/process_documents.feature",
        glue = "co.com.bancolombia.certification.upload.files.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ProcessDocumentRunner {
}
