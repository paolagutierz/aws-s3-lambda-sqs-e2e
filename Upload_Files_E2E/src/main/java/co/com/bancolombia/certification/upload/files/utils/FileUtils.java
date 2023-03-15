package co.com.bancolombia.certification.upload.files.utils;

import co.com.bancolombia.certification.upload.files.exceptions.GeneralException;

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
        } catch (Exception e) {
            new GeneralException("Error on generateNewFile [oldName:" + originPath
                    + "][newName:" + targetPath + "]", e);
        }
        return newName;
    }

    public static void deleteLocalTemporalFile(String temporalFile) {
        try {
            Path path = FileSystems.getDefault().getPath("src/test/resources/files/" + temporalFile);
            Files.delete(path);
        } catch (Exception e) {
            new GeneralException("Error deleting file " + temporalFile);
        }
    }
}
