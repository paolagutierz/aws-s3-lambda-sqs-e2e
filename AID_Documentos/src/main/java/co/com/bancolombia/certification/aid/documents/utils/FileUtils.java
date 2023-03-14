package co.com.bancolombia.certification.aid.documents.utils;

import co.com.bancolombia.certification.aid.documents.exceptions.GeneralException;
import co.com.bancolombia.certification.aid.documents.models.ExecutionMemory;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public static void setUpFileToS3(String documentType) {
        switch (ExecutionMemory.getWorkflowFeatureType()){
            case "carta_laboral":
            case "reintentos":
                ExecutionMemory.setFileExtension(documentType.contains("pdf") ? "pdf" : documentType);
                generateNewFile(documentType);
                break;
            case "declaracion_renta":
                ExecutionMemory.setFileExtension(documentType.split("-")[2]);
                generateNewFile(documentType);
                break;
            case "full_ocr":
            case "id_cards":
                ExecutionMemory.setFileExtension(documentType.split("-")[0]);
                generateNewFile(documentType);
                break;
            default:
                LOGGER.warning(String.format("---> Error on setupFileToS3 - documentType[%1$s] - workFlowFeatureType[%2$s]",
                        documentType, ExecutionMemory.getWorkflowFeatureType()));
                break;
        }
    }

    private static void generateNewFile(String documentType) {
        LOGGER.info("--> Generating file - documentType: " + documentType);
        String oldName = documentType + "." + ExecutionMemory.getFileExtension();
        String newName = documentType + "-" + DataUtils.getDateTime() + "." + ExecutionMemory.getFileExtension();
        String path = "src/test/resources/files/" + ExecutionMemory.getWorkflowFeatureType() + "/<replace>";
        Path originPath = FileSystems.getDefault().getPath(path.replace("<replace>", oldName));
        Path targetPath = FileSystems.getDefault().getPath(path.replace("<replace>", newName));
        ExecutionMemory.setFilePath(targetPath);
        try {
            Files.copy(originPath, targetPath);
            LOGGER.info("--> Generated file - path: " + targetPath);
        } catch (Exception e) {
            new GeneralException("Error on generateNewFile [oldName:" + originPath
                    + "][newName:" + targetPath + "]", e);
        }
        ExecutionMemory.setFileName(newName);
    }

    public static void deleteNewFile() {
        try{
            Files.delete(ExecutionMemory.getFilePath());
        } catch (Exception e){
            new GeneralException("Error deleting file " + ExecutionMemory.getFilePath());
        }
    }
}
