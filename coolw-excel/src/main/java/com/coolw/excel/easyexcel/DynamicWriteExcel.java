package com.coolw.excel.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.coolw.excel.util.DataUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname OperExcelToEasyExcel1
 * @Description 动态生成excel内容
 * @Date 2019/12/19 9:01
 * @Author lw
 */
public class DynamicWriteExcel {

    /**
     * 文件输出路径
     */
    private static String filePath = "D:\\lw\\test_1.xlsx";

    public static void main(String[] args) throws IOException {
        OutputStream os = new FileOutputStream(filePath);
        // 获取excel写入对象
        ExcelWriter writer = EasyExcelFactory.getWriter(os);
        // 创建sheet页签
        Sheet sheet1 = new Sheet(1, 0);
        // 设置sheet页签名称
        sheet1.setSheetName("第一个sheet");

        // 创建一个表格，用于 Sheet 中使用
        Table table1 = new Table(1);
        // 无注解的模式,动态添加表头
        table1.setHead(DataUtil.createListStringHead());

        // 写入数据
        writer.write1(createDynamicModelList(), sheet1, table1);
        writer.finish();

        // 关闭流
        os.close();
    }


    private static List<List<Object>> createDynamicModelList() {
        List<List<Object>> rows = new ArrayList<>();

        for (int i = 1; i < 101; i++) {
            // 一行数据
            List<Object> row = new ArrayList<>();
            row.add("字符串" + i);
            row.add(Long.valueOf(15000994425L + i));
            row.add(Integer.valueOf(i));
            row.add("coody");
            row.add("梦想还是要有的");
            rows.add(row);
        }

        return rows;
    }

}
