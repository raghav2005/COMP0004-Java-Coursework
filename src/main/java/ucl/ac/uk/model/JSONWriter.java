package ucl.ac.uk.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONWriter {

    public static String writeJSON(DataFrame dataFrame) throws JsonProcessingException {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            HashMap<String, String> row = new HashMap<>();
            for (String columnName : dataFrame.getColumnNames()) {
                row.put(columnName, dataFrame.getValue(columnName, i));
            }
            data.add(row);
        }

        HashMap<String, ArrayList<HashMap<String, String>>> jsonData = new HashMap<>();
        jsonData.put("patientData", data);

        return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(jsonData);
    }

}
