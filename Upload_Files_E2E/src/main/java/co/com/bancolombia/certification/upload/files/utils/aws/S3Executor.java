package co.com.bancolombia.certification.upload.files.utils.aws;

import co.com.bancolombia.certification.upload.files.exceptions.S3ServiceException;
import co.com.bancolombia.certification.upload.files.models.ExecutionMemory;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.logging.Logger;

import static co.com.bancolombia.certification.upload.files.utils.FileUtils.getObjectFile;
import static co.com.bancolombia.certification.upload.files.utils.FileUtils.writeLocalFile;

public class S3Executor {
    private static final Logger LOGGER = Logger.getLogger(S3Executor.class.getName());
    private static String sourceBucketName = "s3-e2e-example";
    private static String destinationBucketName = "s3-e2e-example-destination";

    public static String putFileInBucket() {
        String fileName = ExecutionMemory.getFileName();
        String filePath = "src/test/resources/files/" + fileName;
        LOGGER.info(String.format("--> Uploading file to S3 - bucket[%1$s] - localFilePath[%2$s] - s3Key[%3$s] -",
                sourceBucketName, filePath, fileName));
        try {
            S3Client s3 = AmazonS3Client.getS3Client();
            byte[] image= getObjectFile(filePath);
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(sourceBucketName)
                    .key(fileName)
                    .build();
            PutObjectResponse response= s3.putObject(putOb, RequestBody.fromBytes(image));

            LOGGER.info(String.format("--> Uploaded file to S3 - s3://%1$s/%2$s", sourceBucketName, fileName));
            ExecutionMemory.setImage(image);
            return response.eTag();

        } catch (S3ServiceException e) {
            throw new S3ServiceException("Error putFileInBucket: ", e);
        }
    }

    public static void downloadFileDestinationBucket(){
        String fileName = ExecutionMemory.getFileName();
        String filePath = "src/test/resources/thumbnail/" + fileName;
        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(fileName)
                    .bucket(destinationBucketName)
                    .build();

            S3Client s3 = AmazonS3Client.getS3Client();
            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();

            // Write the data to a local file.
            writeLocalFile(filePath, data);
            ExecutionMemory.setThumbnail(data);

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
