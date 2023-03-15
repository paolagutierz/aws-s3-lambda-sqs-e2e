package co.com.bancolombia.certification.upload.files.tasks;

import co.com.bancolombia.certification.upload.files.utils.FileUtils;
import co.com.bancolombia.certification.upload.files.utils.aws.S3Executor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;


public class SendFile implements Task {

    private String file;

    public SendFile(String file) {
        this.file = file;
    }

    @Override
    @Step("{0} send a file to the s3-bucket")
    public <T extends Actor> void performAs(T actor) {
        String temporalFile = FileUtils.createLocalTemporalFile(file);
        S3Executor.putFileInBucket(temporalFile);
        FileUtils.deleteLocalTemporalFile(temporalFile);
    }

    public static SendFile toS3Bucket(String file) {
        return Tasks.instrumented(SendFile.class, file);
    }
}
