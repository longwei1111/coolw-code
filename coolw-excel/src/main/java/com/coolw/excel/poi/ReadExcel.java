package com.coolw.excel.poi;

import com.coolw.excel.util.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname OperExcelToPoi
 * @Description 读取Excel
 * @Date 2019/12/16 10:31
 * @Author lw
 */
public class ReadExcel {

    /**
     * excel文件路径
     */
    private static String excelPath = "D:\\lw\\b_division_info-lw.xlsx";
    /**
     * 写入txt文件路径
     */

    private static String filePath = "D:\\lw\\test.txt";

    public static void main(String[] args) throws IOException {
        excelConvertSql(excelPath);
    }

    public static void excelConvertSql(String excelPath) throws IOException {
        System.out.println("===读取excel路径：" + excelPath);

        File file = new File(excelPath);
        if (!file.exists()) {
            throw new RuntimeException("===error：excel path is not exist");
        }

        InputStream is = new FileInputStream(excelPath);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("test-data");
        int lastRowNum = xssfSheet.getLastRowNum();
        System.out.println("===读取excel总行数：" + lastRowNum);

        List<String> sqlList = new ArrayList<>();
        XSSFRow xssfRow_1 = null;
        XSSFRow xssfRow_2 = null;
        XSSFRow xssfRow_3 = null;
        for (int i = 0; i < lastRowNum; i++) {
            StringBuilder sb = new StringBuilder("(");

            XSSFRow xssfRow = xssfSheet.getRow(i);
            XSSFCell cell_0 = xssfRow.getCell(0);
            XSSFCell cell_1 = xssfRow.getCell(1);
            XSSFCell cell_2 = xssfRow.getCell(2);
            if (cell_0.getCellType() != CellType.BLANK) {
                xssfRow_1 = xssfRow;
                // 组装第一级别sql-value
                FileUtils.assembleFirstLevel(xssfRow_1, sb);
            } else if (cell_0.getCellType() == CellType.BLANK && cell_1.getCellType() != CellType.BLANK) {
                xssfRow_2 = xssfRow;
                // 组装第二级别sql-value
                FileUtils.assembleSecondLevel(xssfRow_1, xssfRow_2, sb);
            } else if (cell_0.getCellType() == CellType.BLANK && cell_1.getCellType() == CellType.BLANK
                    && cell_2.getCellType() != CellType.BLANK) {
                xssfRow_3 = xssfRow;
                // 组装第三级别sql-value
                FileUtils.assembleThirdLevel(xssfRow_1, xssfRow_2, xssfRow_3, sb);
            } else {
                throw new RuntimeException("===assemble data type error");
            }

            if (i == lastRowNum - 1) { // 最后一条数据
                sb.append(");");
            } else {
                sb.append("),").append("\n");
            }

            sqlList.add(sb.toString());
        }

        fileWrite(filePath, sqlList);
    }

    public static void fileWrite(String filePath, List<String> sqlList) {
        System.out.println("===写入文件路径：" + filePath);
        System.out.println("===添加sqlList size：" + sqlList.size());

        File file = new File(filePath);
        if (!file.exists()) { // 写入文件不存在，则创建
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("===写入文件不存在，则创建文件：" + filePath + "失败！", e);
            }
            System.out.println("===写入文件不存在，则创建文件：" + file.getAbsolutePath());
        }

        OutputStream out = null;
        try {
            out = new FileOutputStream(file);

            // insert-field写入文件
            String insertSql = "insert into `b_division_info` (`code`, `name`, `fullname`, `level`, `parent_code`) values \n";
            byte[] insertSqlBytes = insertSql.getBytes();
            out.write(insertSqlBytes);

            // insert-value写入文件
            for (int i = 0; i < sqlList.size(); i++) {
                // 读取输出流中的字节
                byte[] bytes = sqlList.get(i).getBytes();
                // 写入文件
                out.write(bytes);
            }
            System.out.println("===写入文件" + file.getAbsolutePath() + "成功！");
        } catch (IOException e) {
            throw new RuntimeException("===写入文件" + file.getAbsolutePath() + "失败！", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException("===写入文件" + file.getAbsolutePath() + "失败！", e);
                }
            }
        }
    }

}
