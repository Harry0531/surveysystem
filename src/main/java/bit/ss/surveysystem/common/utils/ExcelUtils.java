package bit.ss.surveysystem.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class ExcelUtils {

    // 获取excel文件中指定sheet
    public static Sheet getSheet(File excel, int sheetIndex) throws IOException {
        // 创建workbook
        InputStream is;
        Workbook workbook;
        is = new FileInputStream(excel.getPath());
        workbook = new XSSFWorkbook(is);
        // 只取第一个sheet
        return workbook.getSheetAt(sheetIndex);
    }

    // 通过table中的字段类型获取对应excel类型值
    public static Object getCellValueByFieldType(Cell cell, String fieldType) {
        Object val = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            switch (fieldType) {
                case "varchar":
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    val = cell.getStringCellValue();
                    break;
                case "int":
                    val = (int) cell.getNumericCellValue();
                    break;
                case "decimal":
                    val = cell.getStringCellValue();
                    if (!StringUtils.isDigit((String) val)) {
                        val = "0";
                    }
                    break;
                case "float":
                    val = (float) cell.getNumericCellValue();
                    break;
                case "double":
                    val = cell.getNumericCellValue();
                    break;
                case "datetime":
                case "timestamp":
                case "date":
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
//                        val = dateFormat.parse(cell.getStringCellValue().replaceAll("/", "-"));
                        val = cell.getStringCellValue();
                    } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        val = cell.getDateCellValue();
                        val = dateFormat.format(val);
                    }
                    break;
                default:
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    val = cell.getStringCellValue();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            // do nothing
        }
        return val;
    }

    // 获取excel行中的指定列中的值
    public static Object getCellValue(Cell cell) {
        Object val;
        try {
            if (cell != null) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        val = cell.getNumericCellValue();
                        break;
                    case Cell.CELL_TYPE_STRING:
                        val = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        val = cell.getCellFormula();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        val = cell.getErrorCellValue();
                        break;
                    default:
                        val = null;
                        break;
                }
            } else val = null;
        } catch (Exception e) {
            return null;
        }
        return val;
    }
}