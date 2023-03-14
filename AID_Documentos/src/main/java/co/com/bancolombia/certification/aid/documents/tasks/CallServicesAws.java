package co.com.bancolombia.certification.aid.documents.tasks;

import utils.aws.S3Client;
import com.amazonaws.services.lambda.model.ServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.opencsv.exceptions.CsvValidationException;
import models.ExecutionJson;
import utils.JsonUtils;
import java.io.File;
import java.io.IOException;
import utils.CsvReader;

public class CallServicesAws {

    public static String createLambdaEvent(String bucketName, String arnName, String typeTest) {
        if (!typeTest.equals("204")) {
            typeTest = "pruebas/campana/Feature" + typeTest;
        }
        JsonUtils.convertJsonToString(bucketName, arnName, typeTest + ".csv");
        return ExecutionJson.getBody();
    }

    public static String putFileInBucket(String typeTest, String bucketName) throws IOException, CsvValidationException {
        String fileObjKeyName = "pruebas/campana/Feature" + typeTest + ".csv";
        String fileName = CsvReader.dataReading(typeTest);
        try {
            AmazonS3 s3 = S3Client.getS3Client();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
            s3.putObject(request);
            return "file in bucket";
        } catch (ServiceException e) {
            System.out.println(e);
        }
        return "file is not in bucket";
    }

}