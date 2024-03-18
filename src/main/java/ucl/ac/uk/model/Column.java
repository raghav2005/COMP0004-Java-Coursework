package ucl.ac.uk.model;

import java.util.ArrayList;

public class Column {

    private String name;
    private ArrayList<String> rows = new ArrayList<>();

    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return rows.size();
    }

    public String getRowValue(int rowNumber) { // indexing begins at 1
        return rows.get(rowNumber - 1);
    }

    public void setRowValue(int rowNumber, String newValue) { // indexing begins at 1
        rows.set(rowNumber - 1, newValue);
    }

    public void addRowValue(String newValue) {
        rows.add(newValue);
    }

}
