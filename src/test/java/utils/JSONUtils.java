package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class JSONUtils {

    public static String readJsonFile(String fileName){
        try {
            InputStream is = JSONUtils.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);
            return new String(is.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object[][] readExcelData(String fileName, String sheetName) throws IOException{
      InputStream is= JSONUtils.class.getClassLoader().getResourceAsStream(fileName);
        Workbook workbook= new XSSFWorkbook(is);
        Sheet sheet= workbook.getSheet(sheetName);

        int rowCount= sheet.getPhysicalNumberOfRows();
        int colCount= sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data= new Object[rowCount-1][colCount];

        for(int i=1;i<rowCount;i++){
            Row row= sheet.getRow(i);
            for(int j=0;j<colCount;j++){
                data[i-1][j]=getCellValue(row.getCell(j));
            }
        }
        workbook.close();
        return data;
    }
    private static String getCellValue(Cell cell){
        switch(cell.getCellType()){
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Check if it is whole number!
                if(cell.getNumericCellValue() ==
                        Math.floor(cell.getNumericCellValue())){
                    return String.valueOf((int)cell.getNumericCellValue());
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
