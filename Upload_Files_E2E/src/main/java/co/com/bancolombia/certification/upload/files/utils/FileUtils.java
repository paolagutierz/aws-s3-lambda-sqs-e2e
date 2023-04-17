package co.com.bancolombia.certification.upload.files.utils;

import co.com.bancolombia.certification.upload.files.exceptions.GeneralException;
import co.com.bancolombia.certification.upload.files.models.ExecutionMemory;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public static String createLocalTemporalFile(String document) {
        String[] data = document.split("\\.");
        String documentName = data[0];
        String documentExtension = data[1];
        return createCopy(documentName, documentExtension);
    }

    private static String createCopy(String documentName, String documentExtension) {
        LOGGER.info("--> Generating file - document: " + documentName + "." + documentExtension);
        String oldName = documentName + "." + documentExtension;
        String newName = documentName + "-" + UUID.randomUUID() + "." + documentExtension;
        String path = "src/test/resources/files/<replace>";
        Path originPath = FileSystems.getDefault().getPath(path.replace("<replace>", oldName));
        Path targetPath = FileSystems.getDefault().getPath(path.replace("<replace>", newName));
        try {
            Files.copy(originPath, targetPath);
            LOGGER.info("--> Generated file - path: " + targetPath);
            ExecutionMemory.setFileName(newName);
        } catch (Exception e) {
            new GeneralException("Error on generateNewFile [oldName:" + originPath
                    + "][newName:" + targetPath + "]", e);
        }
        return newName;
    }

    public static void deleteLocalTemporalFile() {
        String temporalFile = ExecutionMemory.getFileName();
        try {
            Path path = FileSystems.getDefault().getPath("src/test/resources/files/" + temporalFile);
            Files.delete(path);
        } catch (Exception e) {
            new GeneralException("Error deleting file " + temporalFile);
        }
    }

    public static byte[] getObjectFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {
            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bytesArray;
    }

    public static void writeLocalFile (String path, byte[] data){
        File myFile = new File(path );
        OutputStream os = null;

        try {
            os = new FileOutputStream(myFile);
            os.write(data);
            System.out.println("Successfully obtained bytes from an S3 object");
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
