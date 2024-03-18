package ucl.ac.uk.model;

import java.util.ArrayList;
import java.util.Optional;

public class DataFrame {

    private ArrayList<Column> columns = new ArrayList<>();

    public void addColumn(String columnName) {
        columns.add(new Column(columnName));
    }

    public ArrayList<String> getColumnNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Column column : columns) {
            names.add(column.getName());
        }

        return names;
    }

    public int getRowCount() {
        return columns.getFirst().getSize();
    }

    // NOTE: Optional
    public String getValue(String columnName, int row) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                return column.getRowValue(row);
            }
        }

        return "";
    }

    public void putValue(String columnName, int row, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.setRowValue(row, value);
            }
        }
    }

    public void addValue(String columnName, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.addRowValue(value);
            }
        }
    }

}
