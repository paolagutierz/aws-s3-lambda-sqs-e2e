package co.com.bancolombia.certification.aid.utils;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class DataUtils {

    private DataUtils(){
        //Do nothing.
    }

    public static String cleanSpecialCaracters(String response){
        response = new String(response.getBytes(ISO_8859_1), UTF_8);
        return response;
    }

    public static String getTimeStamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
        ZoneId zoneId = ZoneId.of("America/Bogota");
        ZonedDateTime timeZone = ZonedDateTime.now(zoneId);
        return formatter.format(timeZone);
    }

    public static String getUUIDv4(){
        return UUID.randomUUID().toString();
    }

    public static String getIdString(){
        return String.valueOf((int) (20000 * Math.random()));
    }
}
