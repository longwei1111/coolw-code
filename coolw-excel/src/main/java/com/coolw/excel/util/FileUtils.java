package com.coolw.excel.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * @Classname FileUtils
 * @Description 文件工具类
 * @Date 2019/12/16 11:37
 * @Author lw
 */
public class FileUtils {

    public static void assembleFirstLevel(XSSFRow xssfRow_1, StringBuilder sb) {
        XSSFCell code_1 = xssfRow_1.getCell(0);
        code_1.setCellType(CellType.STRING);
        XSSFCell name_1 = xssfRow_1.getCell(1);
        name_1.setCellType(CellType.STRING);

        sb.append("'").append(code_1).append("', ");
        sb.append("'").append(name_1).append("', ");
        sb.append("'").append(name_1).append("', ");
        sb.append("'").append("1").append("', ");
        sb.append("'").append("NULL").append("'");
    }

    public static void assembleSecondLevel(XSSFRow xssfRow_1, XSSFRow xssfRow_2, StringBuilder sb) {
        XSSFCell code_1 = xssfRow_1.getCell(0);
        code_1.setCellType(CellType.STRING);
        XSSFCell name_1 = xssfRow_1.getCell(1);
        name_1.setCellType(CellType.STRING);
        XSSFCell code_2 = xssfRow_2.getCell(1);
        code_2.setCellType(CellType.STRING);
        XSSFCell name_2 = xssfRow_2.getCell(2);
        name_2.setCellType(CellType.STRING);

        sb.append("'").append(code_2).append("', ");
        sb.append("'").append(name_1).append(name_2).append("', ");
        sb.append("'").append(name_1).append(name_2).append("', ");
        sb.append("'").append("2").append("', ");
        sb.append("'").append(code_1).append("'");
    }

    public static void assembleThirdLevel(XSSFRow xssfRow_1, XSSFRow xssfRow_2, XSSFRow xssfRow_3, StringBuilder sb) {
        XSSFCell name_1 = xssfRow_1.getCell(1);
        name_1.setCellType(CellType.STRING);
        XSSFCell code_2 = xssfRow_2.getCell(1);
        code_2.setCellType(CellType.STRING);
        XSSFCell name_2 = xssfRow_2.getCell(2);
        name_2.setCellType(CellType.STRING);
        XSSFCell code_3 = xssfRow_3.getCell(2);
        code_3.setCellType(CellType.STRING);
        XSSFCell name_3 = xssfRow_3.getCell(3);
        name_3.setCellType(CellType.STRING);

        sb.append("'").append(code_3).append("', ");
        sb.append("'").append(name_1).append(name_2).append(name_3).append("', ");
        sb.append("'").append(name_1).append(name_2).append(name_3).append("', ");
        sb.append("'").append("3").append("', ");
        sb.append("'").append(code_2).append("'");
    }

}
