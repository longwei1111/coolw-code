package com.coolw.code.thread.excel.util;

import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.TableStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DataUtil
 * @Description 数据工具类
 * @Date 2019/12/19 12:42
 * @Author lw
 */
public class DataUtil {

    public static List<List<String>> createListStringHead() {
        List<List<String>> head = new ArrayList<>();
        List<String> headColumn1 = new ArrayList<>();
        List<String> headColumn2 = new ArrayList<>();
        List<String> headColumn3 = new ArrayList<>();
        List<String> headColumn4 = new ArrayList<>();
        List<String> headColumn5 = new ArrayList<>();

        headColumn1.add("第一列");
        headColumn1.add("第一列");
        headColumn1.add("第一列");
        headColumn2.add("第一列");
        headColumn2.add("第一列");
        headColumn2.add("第一列");
        headColumn3.add("第二列");
        headColumn3.add("第二列");
        headColumn3.add("第二列");
        headColumn4.add("第三列");
        headColumn4.add("第三列2");
        headColumn4.add("第三列2");
        headColumn5.add("第一列");
        headColumn5.add("第3列");
        headColumn5.add("第4列");

        head.add(headColumn1);
        head.add(headColumn2);
        head.add(headColumn3);
        head.add(headColumn4);
        head.add(headColumn5);
        return head;
    }

    public static TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        // 设置表头样式
        Font headFont = new Font();
        // 字体是否加粗
        headFont.setBold(true);
        // 字体大小
        headFont.setFontHeightInPoints((short) 12);
        // 字体
        headFont.setFontName("楷体");
        tableStyle.setTableHeadFont(headFont);
        // 背景颜色
        tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);

        // 设置表格主体样式
        Font contentFont = new Font();
        contentFont.setBold(true);
        contentFont.setFontHeightInPoints((short) 12);
        contentFont.setFontName("黑体");
        tableStyle.setTableHeadFont(contentFont);
        tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);

        return tableStyle;
    }


}
