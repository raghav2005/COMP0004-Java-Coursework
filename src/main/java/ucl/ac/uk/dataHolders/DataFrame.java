package ucl.ac.uk.dataHolders;

import java.util.ArrayList;
import java.util.Optional;

public class DataFrame {

    public ArrayList<Column> columns = new ArrayList<>();

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
        return columns.getFirst().getSize();
    }

    // NOTE: Optional
    public Optional<String> getValue(String columnName, int row) { // indexing begins at 1
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                return Optional.of(column.getRowValue(row));
            }
        }

        return Optional.empty();
    }

    public void putValue(String columnName, int row, String value) { // indexing begins at 1
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
