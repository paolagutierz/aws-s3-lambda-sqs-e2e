package co.com.bancolombia.certification.aid.documents.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static String dataReading(String typeTest) throws IOException, CsvValidationException {
        String originalCSV= "src/test/resources/OriginalDataSet.csv";
        String pathFile = null;
        List<String[]>  registros = new ArrayList<>();
        FileReader filereader = new FileReader(originalCSV);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
        try {
            registros = csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        return pathFile=DataUtils.createFile(registros, typeTest);
    }
}
