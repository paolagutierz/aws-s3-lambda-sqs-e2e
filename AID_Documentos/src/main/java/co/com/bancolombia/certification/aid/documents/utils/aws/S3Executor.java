package co.com.bancolombia.certification.aid.documents.utils.aws;

import co.com.bancolombia.certification.aid.documents.exceptions.S3ServiceException;
import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class S3Executor {

    private static final Logger LOGGER = Logger.getLogger(S3Executor.class.getName());

    private static String bucketName = String.format("example-%s-s3-raw", System.getProperty("environment"));
    private static String resultsBucketName = String.format("example-%s-s3-results", System.getProperty("environment"));

    public static void putFileInBucket(String fileName, String workflow){
        String filePath = "src/test/resources/files/" + workflow + "/" + fileName;
        String fileKeyOnS3 = !fileName.contains("reintentos") ? "caso_uno/" + workflow + "/" + fileName : "fraude/test/" + fileName;
        LOGGER.info(String.format("--> Uploading file to S3 - bucket[%1$s] - localFilePath[%2$s] - s3Key[%3$s] -",
                        bucketName, filePath, fileKeyOnS3));
        try {
            AmazonS3 s3 = S3Client.getS3Client();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileKeyOnS3, new File(filePath));
            s3.putObject(request);
            ExecutionMemory.setS3RawPath("s3://" + bucketName + "/" + fileKeyOnS3);
            LOGGER.info(String.format("--> Uploaded file to S3 - s3://%1$s/%2$s", bucketName, fileKeyOnS3));
        } catch (S3ServiceException e) {
            throw new S3ServiceException("Error putFileInBucket: ", e);
        }
    }

    public static String getObjectS3(String fileObjKeyName, String fileName) {
        String filePath = "src/test/resources/files/" + ExecutionMemory.getWorkflowFeatureType() + "/" + fileName;
        try {
            LOGGER.info(String.format("--> Downloading file from S3 - bucket [%1$s] - s3Key [%2$s] - localFileName [%3$s] -",
                    resultsBucketName, fileObjKeyName, fileName));
            AmazonS3 s3 = S3Client.getS3Client();
            S3Object object = s3.getObject(resultsBucketName, fileObjKeyName);
            S3ObjectInputStream s3is = object.getObjectContent();
            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        LOGGER.info(String.format("--> File [%1$s] Downloaded", fileName));
        return filePath;
    }

}
