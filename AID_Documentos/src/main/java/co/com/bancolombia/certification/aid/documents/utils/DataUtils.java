package co.com.bancolombia.certification.aid.documents.utils;

import com.amazonaws.services.lambda.model.InvokeResult;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


public class DataUtils {


    public static String convertBase64ToString(String chain) {
        byte[] decodedBytes = Base64.getDecoder().decode(chain);
        return new String(decodedBytes);
    }

    public static String payloadExtractor(InvokeResult lambdaResult) {
        return new String(lambdaResult.getPayload().array(), StandardCharsets.UTF_8);
    }

    public static String createFile(List<String[]> file, String scenario) throws IOException {
        String pathFile = null;
        switch (scenario){
            case "200":
                pathFile= ReplaceCsv.replaceValuesCsv200(file);
                break;
            case "204":
                pathFile= ReplaceCsv.replaceValuesCsv200(file);
                break;
            case "206":
                pathFile= ReplaceCsv.replaceValuesCsv206(file);
                break;
            case "503":
                pathFile= ReplaceCsv.replaceValuesCsv503(file);
                break;
            default:
                System.out.println("ERROR WITH PARAMETER CONTROL: " + scenario);
                break;
        }
        return pathFile;
    }

    public static String getDate(){
        String pattern = "yyyy-MM-dd HH:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("es", "CO"));
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return simpleDateFormat.format(new Date());
    }


}

