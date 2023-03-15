package co.com.bancolombia.certification.upload.files.utils.aws;

import co.com.bancolombia.certification.upload.files.exceptions.S3ServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.util.logging.Logger;

public class S3Executor {
    private static final Logger LOGGER = Logger.getLogger(S3Executor.class.getName());
    private static String bucketName = "s3trigger-s3bucketfbfa637e-1rsu7edoux805";

    public static void putFileInBucket(String fileName) {
        String filePath = "src/test/resources/files/" + fileName;
        LOGGER.info(String.format("--> Uploading file to S3 - bucket[%1$s] - localFilePath[%2$s] - s3Key[%3$s] -",
                bucketName, filePath, fileName));
        try {
            AmazonS3 s3 = S3Client.getS3Client();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, new File(filePath));
            s3.putObject(request);
            LOGGER.info(String.format("--> Uploaded file to S3 - s3://%1$s/%2$s", bucketName, fileName));
        } catch (S3ServiceException e) {
            throw new S3ServiceException("Error putFileInBucket: ", e);
        }
    }
}
