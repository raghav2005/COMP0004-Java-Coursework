package ucl.ac.uk.model;

import java.util.ArrayList;

public class Column {

    private String name;
    private ArrayList<String> rows;

    public Column(String name) {
        this.name = name;
        this.rows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return rows.size();
    }

    public String getRowValue(int rowNumber) {
        return rows.get(rowNumber);
    }

    public void setRowValue(int rowNumber, String newValue) {
        rows.set(rowNumber, newValue);
    }

    public void addRowValue(String newValue) {
        rows.add(newValue);
    }

}
