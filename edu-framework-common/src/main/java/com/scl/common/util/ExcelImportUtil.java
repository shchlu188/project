package com.scl.common.util;

import io.swagger.models.auth.In;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/26
 * Description:
 */
@Data
public class ExcelImportUtil {
    private HSSFFormulaEvaluator formulaEvaluator;
    private HSSFSheet sheet;
    private String pattern;

    public ExcelImportUtil() {
    }

    public ExcelImportUtil(InputStream is) throws IOException {
        this(is, 0, true);
    }

    public ExcelImportUtil(InputStream inputStream, int sheetIndex) throws IOException {
        this(inputStream, sheetIndex, true);
    }

    public ExcelImportUtil(InputStream inputStream, int sheetIndex, boolean evaluateFormular) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        if (evaluateFormular)
            this.formulaEvaluator = new HSSFFormulaEvaluator(workbook);
    }

    public String getCellValue(Cell cell) throws Exception {
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (pattern != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        String dateStr = sdf.format(date);
                        return dateStr;
                    } else {
                        return date.toString();
                    }
                } else {
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    return cell.toString();
                }

            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_FORMULA:
                if (this.formulaEvaluator == null)
                    return cell.getCellFormula();
                else
                    return this.formulaEvaluator
                            .evaluate(cell)
                            .formatAsString();
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_ERROR:

            default:
                throw new Exception("excel数据类型错误");

        }

    }
}
