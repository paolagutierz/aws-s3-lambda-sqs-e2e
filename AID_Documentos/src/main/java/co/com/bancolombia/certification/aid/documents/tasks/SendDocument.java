package co.com.bancolombia.certification.aid.documents.tasks;

import co.com.bancolombia.certification.aid.documents.exceptions.GeneralException;
import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import co.com.bancolombia.certification.aid.documents.utils.FileUtils;
import co.com.bancolombia.certification.aid.documents.utils.aws.S3Executor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;


public class SendDocument implements Task {

    private String documentType;
    private String workflowType;

    public SendDocument(String documentType, String workflowType){
        this.documentType = documentType;
        this.workflowType = workflowType;
        ExecutionMemory.setDocumentType(documentType);
        ExecutionMemory.setWorkflowFeatureType(workflowType);
    }

    @Step("{0} send a document to the s3-raw-bucket to be processed by the AID platform")
    @Override
    public <T extends Actor> void performAs(T actor) {
        if (ExecutionMemory.getWorkflowArgType().toLowerCase().contains(workflowType.toLowerCase())){
            ExecutionMemory.setRunTest(true);
            FileUtils.setUpFileToS3(documentType);
            S3Executor.putFileInBucket(ExecutionMemory.getFileName(), ExecutionMemory.getWorkflowFeatureType());
            FileUtils.deleteNewFile(); //Deletes file created in project
            if (ExecutionMemory.getWorkflowFeatureType().contains("id_cards")){
                try {
                    Thread.sleep(120000);
                } catch (Exception e){
                    new GeneralException("---> Error on id_cards sleep before dynamo result validation: ", e);
                }
            }
        } else {
            ExecutionMemory.setRunTest(false);
        }
    }

    public static SendDocument toS3Bucket(String documentType, String workflowType){
        return Tasks.instrumented(SendDocument.class, documentType, workflowType);
    }

}
