package utilities;



import org.apache.poi.ss.usermodel.*;


import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadSheet(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*to the output will be
    ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx", "Sheet1");

    int rows = excel.getRowCount();
    int cols = excel.getColumnCount();
        System.out.println("Rows: " + rows + " | Cols: " + cols);

    String value = excel.getCellData(1, 0);
        System.out.println("Cell Value: " + value);

        excel.setCellData(1, 1, "PASS");
        excel.closeWorkbook();*/
    // Get all data as 2D Object array (useful for TestNG DataProvider)
    public Object[][] getSheetDataAsArray() {
        int rows = getRowCount();
        int cols = getColumnCount();
        Object[][] data = new Object[rows - 1][cols]; // skipping header row

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }




    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    public String getCellData(int row, int col) {
        Row r = sheet.getRow(row);
        Cell c = r.getCell(col);
        return c == null ? "" : c.toString();
    }
    // Get all data as List of Map (useful for Cucumber DataTable style)
    public List<Map<String, String>> getSheetDataAsList() {
        List<Map<String, String>> allData = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        int rows = getRowCount();
        int cols = getColumnCount();

        for (int i = 1; i < rows; i++) {
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < cols; j++) {
                String key = headerRow.getCell(j).getStringCellValue();
                String value = getCellData(i, j);
                rowData.put(key, value);
            }
            allData.add(rowData);
        }
        return allData;
    }

}
