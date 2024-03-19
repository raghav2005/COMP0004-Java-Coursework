package ucl.ac.uk.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            ArrayList<String> row = null;

            for (String columnName : dataFrame.getColumnNames()) {
                if (dataFrame.getValue(columnName, i).contains(searchWord)) {
                    row = dataFrame.getRow(i);
                }
            }

            if (!(row == null)) allRows.add(row);
        }

        return allRows;

    }

}
