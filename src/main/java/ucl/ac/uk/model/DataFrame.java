package ucl.ac.uk.model;

import java.util.ArrayList;

public class DataFrame {

    private ArrayList<Column> columns;

    public DataFrame() {
        this.columns = new ArrayList<>();
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public ArrayList<String> getColumnNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Column column : columns) {
            names.add(column.getName());
        }

        return names;
    }

    public int getRowCount() {
        if (columns.isEmpty()) return 0;
        else return columns.getFirst().getSize();
    }

    public String getValue(String columnName, int row) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                return column.getRowValue(row);
            }
        }

        return null;
    }

    public void putValue(String columnName, int row, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.setRowValue(row, value);
                break;
            }
        }
    }

    public void addValue(String columnName, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.addRowValue(value);
                break;
            }
        }
    }

    public ArrayList<String> getRow(int rowNumber) {
        ArrayList<String> row = new ArrayList<>();
        for (Column column : columns) {
            row.add(getValue(column.getName(), rowNumber));
        }

        return row;
    }

}
