package Service;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportingCSV {

    public int exportToCSV(JTable table, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        // Write the column headers to the file
        for (int i = 0; i < table.getColumnCount(); i++) {
            bw.write(table.getColumnName(i));
            if (i != table.getColumnCount() - 1) {
                bw.write(",");
            }
        }
        bw.write("\n");

        // Write the data to the file
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                Object value = table.getValueAt(i, j);
                if (value != null) {
                    bw.write(value.toString());
                }
                if (j != table.getColumnCount() - 1) {
                    bw.write(",");
                }
            }
            bw.write("\n");
        }

        // Close the file
        bw.close();
        fw.close();
        return 1;
    }
}
