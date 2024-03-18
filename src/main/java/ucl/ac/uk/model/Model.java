package ucl.ac.uk.model;

import java.io.IOException;

public class Model {

    private DataFrame dataFrame;

    public void loadData(String filename) throws IOException {
        try {
            dataFrame = new DataLoader().loadData(filename);
        } catch (IOException exception) {
            throw new IOException(exception);
        }
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }

}
