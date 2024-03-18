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

}
