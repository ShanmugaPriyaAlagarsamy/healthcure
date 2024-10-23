package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutility {

    public FileInputStream fis;
    public FileOutputStream fileOut;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public String path;

    public Excelutility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetname) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowcount;
    }

    public int getCellCount(String sheetname, int rownum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fis.close();
        return cellcount;
    }

    public String getCellData(String sheetname, int rownum, int colnum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);
        String data;
        DataFormatter formatter = new DataFormatter();

        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fis.close();
        return data;
    }

    public void setCellData(String sheetname, int rownum, int colnum, String data) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        workbook.close();
        fis.close();
        fileOut.close();
    }

}
