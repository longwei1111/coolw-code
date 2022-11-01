package com.coolw.code.thread.excel.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.coolw.code.thread.excel.model.WriteModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname OperExcelToEasyExcel
 * @Description easyexcel使用
 * @Date 2019/12/19 9:01
 * @Author lw
 */
public class ModelWriteExcel {

    /**
     * 文件输出路径
     */
    private static String filePath = "D:\\lw\\test_1.xlsx";

    public static void main(String[] args) throws IOException {
        OutputStream os = new FileOutputStream(filePath);
        // 创建excel写入对象
        ExcelWriter writer = EasyExcelFactory.getWriter(os);
        // 创建sheet页签
        Sheet sheet1 = new Sheet(1, 0, WriteModel.class);
        // 设置sheet页签名称
        sheet1.setSheetName("第一个sheet");

        // 写入数据
        writer.write(createModelList(), sheet1);
        writer.finish();

        // 关闭流
        os.close();
    }


    private static List<WriteModel> createModelList() {
        List<WriteModel> writeModels = new ArrayList<>();

        for (int i = 1; i < 101; i++) {
            WriteModel writeModel = new WriteModel();
            writeModel.setSerialNumber(i);
            writeModel.setName("coody_" + i);
            writeModel.setPassword("123456");
            writeModels.add(writeModel);
        }

        return writeModels;
    }

}
