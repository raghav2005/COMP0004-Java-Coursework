package ucl.ac.uk.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Model {

    private DataFrame dataFrame;

    public void readData(String filename) throws IOException {
        try {
            dataFrame = new DataLoader().loadData(filename);
        } catch (IOException exception) {
            throw new IOException(exception);
        }
    }

    public DataFrame getDataFrame() throws NullPointerException {
        if (dataFrame == null) {
            throw new NullPointerException();
        }

        return dataFrame;
    }

    public ArrayList<String> getDataFiles() throws IOException {
        ArrayList<String> dataFiles = new ArrayList<>();
        File folder = new File(System.getProperty("user.dir") + "/data");
        File[] files = folder.listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                dataFiles.add(file.getName());
            }
        }

        return dataFiles;

    }

    public ArrayList<ArrayList<String>> getAllRows() {
        ArrayList<ArrayList<String>> allRows = new ArrayList<>();
        ArrayList<String> row;

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            row = new ArrayList<>();
            for (String columnName : dataFrame.getColumnNames()) {
                row.add(dataFrame.getValue(columnName, i));
            }
            allRows.add(row);
        }

        return allRows;

    }

    public ArrayList<ArrayList<String>> search(String searchWord) {
        ArrayList<ArrayList<String>> allRows = new ArrayList<>();
        searchWord = searchWord == null ? "" : searchWord;

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            ArrayList<String> row = null;

            for (String columnName : dataFrame.getColumnNames()) {
                if (dataFrame.getValue(columnName, i).toLowerCase().contains(searchWord.toLowerCase())) {
                    row = dataFrame.getRow(i);
                }
            }

            if (!(row == null)) allRows.add(row);
        }

        return allRows;

    }

    // POTENTIAL ADDITIONAL FEATURE: button for specific search / fuzzy search

    public ArrayList<ArrayList<String>> sort(String columnName, ArrayList<ArrayList<String>> allRows, boolean reversed) {
        if (columnName == null || columnName.isEmpty()) return allRows;

        ArrayList<String> columnNames = dataFrame.getColumnNames();
        int columnIndex = IntStream.range(0, columnNames.size()).filter(i -> columnNames.get(i).equals(columnName)).findFirst().orElse(0);
        Stream<ArrayList<String>> necessaryData = allRows.stream().sorted(Comparator.comparing(row -> row.get(columnIndex)));

        return !reversed ? necessaryData.collect(Collectors.toCollection(ArrayList::new)) : new ArrayList<>(necessaryData.collect(Collectors.toList()).reversed());
    }

    private void writeData(String filename, ArrayList<ArrayList<String>> allRows) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            int numberOfColumns = dataFrame.getColumnNames().size();

            for (int i = 0; i < numberOfColumns; i++) {
                if (i == numberOfColumns - 1) writer.write(dataFrame.getColumnNames().get(i));
                else writer.write(dataFrame.getColumnNames().get(i) + ",");
            }
            writer.newLine();

            for (ArrayList<String> row : allRows) {
                int rowSize = row.size();
                for (int i = 0; i < rowSize; i ++) {
                    if (i == rowSize - 1) writer.write(row.get(i));
                    else writer.write(row.get(i) + ",");
                }
                writer.newLine();
            }

        } catch (IOException exception) {
            throw new IOException("Error writing data to file: " + exception.getMessage());
        }

    }

    public void deleteAndWrite(String originalFilename, String id) throws IOException {
        ArrayList<ArrayList<String>> allRows = getAllRows().stream().filter(row -> !row.getFirst().equals(id)).collect(Collectors.toCollection(ArrayList::new));

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String fileNameNoExtension = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String newFilename = fileNameNoExtension.substring(0, !fileNameNoExtension.contains("_") ? fileNameNoExtension.length() : fileNameNoExtension.indexOf("_")) + "_" + formattedDateTime + ".csv";

        try {
            writeData(newFilename, allRows);
        } catch (IOException exception) {
            throw new IOException(exception.getMessage());
        }
        readData(newFilename);

    }

    public String generateUniqueUUID() {
        ArrayList<ArrayList<String>> allRows = getAllRows();
        String randomUUID;
        boolean exists;

        do {
            randomUUID = UUID.randomUUID().toString();
            String copyRandomUUID = randomUUID;
            exists = allRows.stream().anyMatch(row -> row.get(0).equals(copyRandomUUID));
        } while (exists);

        return randomUUID;
    }

}
