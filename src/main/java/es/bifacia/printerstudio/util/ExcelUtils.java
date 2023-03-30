package es.bifacia.printerstudio.util;

import org.apache.poi.ss.usermodel.Cell;

public abstract class ExcelUtils {

    /**
     * Gets the String value of a cell from the Excel.
     * @param cell Cell from which we want to extract the String value.
     * @return String value of the cell.
     */
    public static String getStringValue(final Cell cell) {
        String value = null;
        if (cell != null) {
            value = cell.getStringCellValue();
            if (value != null) {
                value = value.trim();
            }
        }
        return value;
    }

    /**
     * Gets the int value of a cell from the Excel.
      * @param cell Cell from which we want to extract the int value.
     * @return Int value of the cell.
     */
    public static int getIntValue(final Cell cell) {
        int value = 0;
        try {
            value = (int) cell.getNumericCellValue();
        } catch (Exception ex) {
        }
        return value;
    }
}
