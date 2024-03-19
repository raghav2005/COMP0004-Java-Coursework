package ucl.ac.uk.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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

}
