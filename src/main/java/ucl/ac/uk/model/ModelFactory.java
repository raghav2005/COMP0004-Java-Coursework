package ucl.ac.uk.model;

import java.io.IOException;

public class ModelFactory {

    private static Model model;

    public static Model getModel(String filename) throws IOException {
        if (model == null) {
            model = new Model();
            model.loadData(filename);
        }
        return model;
    }

}
