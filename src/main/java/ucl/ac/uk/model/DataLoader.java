package ucl.ac.uk.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DataLoader {

    public DataFrame loadData(String filename) throws IOException {
        DataFrame dataFrame = new DataFrame();

        try (Reader reader = new FileReader(filename);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            int counter = 0;
            for (CSVRecord csvRecord : csvParser) {
                if (counter == 0) {
                    for (int i = 0; i < csvRecord.size(); i++) {
                        dataFrame.addColumn(csvRecord.get(i));
                    }
                    counter++;
                }
                else {
                    for (int i = 0; i < csvRecord.size(); i++) {
                        dataFrame.addValue(dataFrame.getColumnNames().get(i), csvRecord.get(i));
                    }
                }
            }

        } catch (IOException exception) {
            System.err.println("IOException: " + exception.getMessage());
            exception.printStackTrace();
            throw new IOException(exception.getCause());
        }

        return dataFrame;

    }

}