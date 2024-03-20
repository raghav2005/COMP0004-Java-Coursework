package ucl.ac.uk.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;
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
                if (file.getName().substring(file.getName().lastIndexOf("."), file.getName().length()).equals(".csv"))  dataFiles.add(file.getName());
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

    private int getAge(String birthDateStr, String deathDateStr) {
        if (birthDateStr == null || birthDateStr.isEmpty()) return -1;

        LocalDate birthDate = LocalDate.parse(birthDateStr);
        LocalDate currentDate = LocalDate.now();
        LocalDate deathDate = null;

        if (deathDateStr != null && !deathDateStr.isEmpty()) {
            deathDate = LocalDate.parse(deathDateStr);
        }

        if (deathDate != null && deathDate.isBefore(birthDate)) return -1;

        Period period;
        if (deathDate != null) period = Period.between(birthDate, deathDate);
        else period = Period.between(birthDate, currentDate);

        return period.getYears();

    }

    public ArrayList<ArrayList<String>> sort(String columnName, ArrayList<ArrayList<String>> allRows, boolean reversed) {
        if (columnName == null || columnName.isEmpty()) return allRows;

        ArrayList<String> columnNames = dataFrame.getColumnNames();

        try {
            if (columnName.equals("AGE") && !columnNames.contains("AGE")) {

                int columnIndexBirth = columnNames.indexOf("BIRTHDATE");
                int columnIndexDeath = columnNames.indexOf("DEATHDATE");

                if (columnIndexBirth == -1 || columnIndexDeath == -1) throw new NullPointerException();

                Comparator<ArrayList<String>> ageComparer = (row1, row2) -> {
                    String birthDateStr1 = row1.get(columnIndexBirth);
                    String deathDateStr1 = row1.get(columnIndexDeath);
                    String birthDateStr2 = row2.get(columnIndexBirth);
                    String deathDateStr2 = row2.get(columnIndexDeath);

                    return Integer.compare(getAge(birthDateStr1, deathDateStr1), getAge(birthDateStr2, deathDateStr2));
                };
                Stream<ArrayList<String>> necessaryData = allRows.stream().sorted(ageComparer);

                return !reversed ? necessaryData.collect(Collectors.toCollection(ArrayList::new)) : new ArrayList<>(necessaryData.collect(Collectors.toList()).reversed());

            }
        } catch (NullPointerException exception) {
            return allRows;
        }

        int columnIndex = columnNames.indexOf(columnName);
//        IntStream.range(0, columnNames.size()).filter(i -> columnNames.get(i).equals(columnName)).findFirst().orElse(0);
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

    public String getNewFilename(String originalFilename) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String fileNameNoExtension = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        return fileNameNoExtension.substring(0, !fileNameNoExtension.contains("_") ? fileNameNoExtension.length() : fileNameNoExtension.indexOf("_")) + "_" + formattedDateTime + ".csv";
    }

    public void deleteAndWrite(String originalFilename, String id) throws IOException {
        ArrayList<ArrayList<String>> allRows = getAllRows().stream().filter(row -> !row.getFirst().equals(id)).collect(Collectors.toCollection(ArrayList::new));
        String newFilename = getNewFilename(originalFilename);

        try {
            writeData(newFilename, allRows);
        } catch (IOException exception) {
            throw new IOException(exception.getMessage());
        }

        try {
            readData(newFilename);
        } catch (IOException exception) {
            throw new IOException(exception);
        }

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

    public void addAndWrite(String originalFilename, ArrayList<String> rowValues) throws IOException {
        ArrayList<ArrayList<String>> allRows = getAllRows();
        allRows.add(rowValues);

        String newFilename = getNewFilename(originalFilename);

        try {
            writeData(newFilename, allRows);
        } catch (IOException exception) {
            throw new IOException(exception.getMessage());
        }

        try {
            readData(newFilename);
        } catch (IOException exception) {
            throw new IOException(exception);
        }

    }

    public void editAndWrite(String originalFilename, ArrayList<String> rowValues, String newRowID) throws IOException {
        ArrayList<ArrayList<String>> allRows = getAllRows();

        for (int i = 0; i < allRows.size(); i++) {
            if (allRows.get(i).getFirst().equals(newRowID)) allRows.set(i, rowValues);
        }

        String newFilename = getNewFilename(originalFilename);

        try {
            writeData(newFilename, allRows);
        } catch (IOException exception) {
            throw new IOException(exception.getMessage());
        }

        try {
            readData(newFilename);
        } catch (IOException exception) {
            throw new IOException(exception);
        }

    }

    public HashMap<String, Integer> getBarChart(String columnName, String searchWord) {
        ArrayList<ArrayList<String>> allRows = search(searchWord);
        ArrayList<String> allColumns = dataFrame.getColumnNames();
        HashMap<String, Integer> frequencies = new HashMap<>();

        if (columnName.equals("AGE") && !dataFrame.getColumnNames().contains("AGE")) {

            int columnIndexBirth = allColumns.indexOf("BIRTHDATE");
            int columnIndexDeath = allColumns.indexOf("DEATHDATE");

            for (ArrayList<String> row : allRows) {
                Integer age = getAge(row.get(columnIndexBirth), row.get(columnIndexDeath));
                if (frequencies.containsKey(age.toString())) {
                    frequencies.put(age.toString(), frequencies.get(age.toString()) + 1);
                } else {
                    frequencies.put(age.toString(), 1);
                }
            }

        } else {

            int columnIndex = 0;

            for (int i = 0; i < allColumns.size(); i++) {
                if (allColumns.get(i).equals(columnName)) {
                    columnIndex = i;
                    break;
                }
            }

            for (ArrayList<String> row : allRows) {
                if (frequencies.containsKey(row.get(columnIndex).toString())) {
                    frequencies.put(row.get(columnIndex).toString(), frequencies.get(row.get(columnIndex).toString()) + 1);
                } else {
                    frequencies.put(row.get(columnIndex).toString(), 1);
                }
            }

        }

        return frequencies;

    }

}
