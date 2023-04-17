package co.com.bancolombia.certification.upload.files.tasks;


import co.com.bancolombia.certification.upload.files.utils.aws.S3Executor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

public class DownloadFile implements Task {

    @Override
    @Step("{2} download the resize image from destination s3-bucket")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        S3Executor.downloadFileDestinationBucket();
    }

    public static DownloadFile fromS3Bucket() {
        return Tasks.instrumented(DownloadFile.class);
    }

}
