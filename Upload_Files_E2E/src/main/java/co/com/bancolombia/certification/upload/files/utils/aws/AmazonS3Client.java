package co.com.bancolombia.certification.upload.files.utils.aws;

import co.com.bancolombia.certification.upload.files.exceptions.S3ServiceException;
import com.amazonaws.AmazonServiceException;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;

public class AmazonS3Client {

    public static S3Client getS3Client() {
        try {
            ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
            Region region = Region.US_EAST_1;
            S3Client s3 = S3Client.builder()
                    .region(region)
                    .credentialsProvider(credentialsProvider)
                    .build();
            return s3;
        } catch (SdkException e) {
            throw new S3ServiceException("Client error [getS3Client]: " + e.getMessage(),e);
        }
    }

}
