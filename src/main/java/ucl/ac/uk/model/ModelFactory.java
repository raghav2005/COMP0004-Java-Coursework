package ucl.ac.uk.model;

import java.io.IOException;

public class ModelFactory {

    private static Model model;

    // Singleton so static
    public static Model getModel(String filename) throws IOException {
        if (model == null) model = new Model();

        try {
            model.readData(filename);
        } catch (IOException exception) {
            throw new IOException(exception);
        }

        return model;
    }

    // overload - don't want to enter filename unnecessarily every time
    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }

        return model;
    }

}
