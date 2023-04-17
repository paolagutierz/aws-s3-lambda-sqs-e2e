package co.com.bancolombia.certification.upload.files.models;

import com.google.gson.JsonObject;

import java.nio.file.Path;

public class ExecutionMemory {
    private static String fileName;
    private static byte[] image;
    private static byte[] thumbnail;

    public static byte[] getImage() {
        return image;
    }

    public static void setImage(byte[] image) {
        ExecutionMemory.image = image;
    }

    public static byte[] getThumbnail() {
        return thumbnail;
    }

    public static void setThumbnail(byte[] thumbnail) {
        ExecutionMemory.thumbnail = thumbnail;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        ExecutionMemory.fileName = fileName;
    }


}
