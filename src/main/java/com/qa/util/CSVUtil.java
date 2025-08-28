package com.qa.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVUtil {

    public static Iterator<Object[]> readCSV(String filePath, boolean skipHeader) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<Object[]> testData = new ArrayList<>();
        String[] nextLine;

        if (skipHeader) {
            reader.readNext(); // Skip header row
        }

        while ((nextLine = reader.readNext()) != null) {
            testData.add(nextLine);
        }
        reader.close();
        return testData.iterator();
    }
}
