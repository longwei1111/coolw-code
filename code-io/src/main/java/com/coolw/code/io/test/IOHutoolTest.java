package com.coolw.code.io.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * TODO
 *
 * @author coolw
 * @version 1.0
 * @date 2023/1/29 14:48
 */
public class IOHutoolTest {

    /**
     * 读文件
     */
    @Test
    public void testReader() {
        // 源文件路径
        String filePath = "D:\\javaspace\\MyProject\\coolw-code\\code-io\\src\\main\\resources\\application.yml";

        FileReader fileReader = new FileReader(filePath);
        // 读取全部字符
        String value = fileReader.readString();
        System.out.println(value);

        System.out.println("============================================");

        // 按行读取字符
        List<String> lines = fileReader.readLines();
        lines.forEach(System.out::println);

        System.out.println("============================================");

        // 读取字节
        byte[] bytes = fileReader.readBytes();
        System.out.println(new String(bytes));
    }

    /**
     * 写文件
     */
    @Test
    public void testWrite() {
        // 源文件路径
        String sourceFilePath = "D:\\javaspace\\MyProject\\coolw-code\\code-io\\src\\main\\resources\\application.yml";
        // 写入文件路径
        String targetFilePath = "D:\\javaspace\\MyProject\\coolw-code\\code-io\\src\\main\\resources\\file\\text3.txt";
        /*FileWriter fileWriter = new FileWriter(targetFilePath);
        fileWriter.write("123");*/

        FileWriter fileWriter = new FileWriter(targetFilePath);
        FileReader fileReader = new FileReader(sourceFilePath);

        // 写入到目标文件
        fileWriter.write(fileReader.readString());
    }

    /**
     * 文件追加内容
     */
    @Test
    public void testAppender() {
        String filePath = "D:\\javaspace\\MyProject\\coolw-code\\code-io\\src\\main\\resources\\file\\text4.txt";
        File file = new File(filePath);

        // 追加文件内容 
        FileAppender appender = new FileAppender(file, 8, true);
        appender.append("1");
        appender.append("2");
        appender.append("3");
        appender.append("4");
        appender.append("5");
        appender.append("6");
        appender.append("7");
        appender.append("8");
        appender.append("9");
        appender.flush();
    }

    /**
     * 文件名获取
     */
    @Test
    public void testFileName() {
        String filePath = "D:\\javaspace\\MyProject\\coolw-code\\code-io\\src\\main\\resources\\file\\text4.txt";
        File file = FileUtil.file(filePath);

        // 文件名称
        String name = FileNameUtil.getName(file);
        System.out.println("name:" + name);

        // 文件扩展名
        String extName = FileNameUtil.extName(file);
        System.out.println("extName:" + extName);

        // 文件名前缀
        String prefix = FileNameUtil.getPrefix(file);
        System.out.println("prefix:" + prefix);

        // 文件名后缀
        String suffix = FileNameUtil.getSuffix(file);
        System.out.println("suffix:" + suffix);
    }
}
