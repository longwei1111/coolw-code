package com.coolw.code.io.test;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import java.io.*;


/**
 * TODO
 *
 * @author coolw
 * @version 1.0
 * @date 2023/1/29 10:55
 */
public class IOTest {

    /**
     * 字节缓冲输入流-读文件
     */
    @Test
    public void testReader1() {
        // 源文件路径
        String sourceFilePath = "src/main/java/com/coolw/code/io/CodeIOApplication.java";

        InputStream inputStream = null;
        // 字节缓冲输入流
        BufferedInputStream bufferedInputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
            bufferedInputStream = new BufferedInputStream(inputStream);

            byte[] arr = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(arr)) != -1) {
                String value = new String(arr, 0, len);
                System.out.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(bufferedInputStream);
        }
    }

    /**
     * 字节缓冲输出流-写文件
     */
    @Test
    public void testWrite1() {
        // 源文件路径
        String sourceFilePath = "src/main/java/com/coolw/code/io/CodeIOApplication.java";
        // 写入到目标文件路径
        String targetFilePath = "src/main/resources/file/text1.txt";

        InputStream inputStream = null;
        // 原始字节输出流
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
            outputStream = new FileOutputStream(targetFilePath);

            // 将原始字节输入流包装为高级的缓存输入流
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            // 将原始字节输出流包装为高级的缓冲输出流
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);

            byte[] arr = new byte[1024];
            int len;
            // 读文件
            while ((len = bis.read(arr)) != -1) {
                // 写文件
                bos.write(arr, 0, len);
            }

            // 刷新(如果不调用此方法,文件内容为空)
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
        }
    }

    /**
     * 字符缓冲输入流-读文件
     */
    @Test
    public void testReader2() {
        // 源文件路径
        String filePath = "src/main/resources/file/text2.txt";

        Reader reader = null;
        // 字符缓冲输入流
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader(filePath);
            bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(reader);
            IoUtil.close(bufferedReader);
        }
    }

    /**
     * 字符缓冲输出流-写文件
     */
    @Test
    public void testWrite2() {
        // 写入文件路径
        String filePath = "src/main/resources/file/text2.txt";

        Writer writer = null;
        // 字符缓冲输出流
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(filePath);
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("你好");
            // 换行
            bufferedWriter.newLine();
            bufferedWriter.write("coolw");
            bufferedWriter.newLine();
            bufferedWriter.write("!!!");

            // 刷新(如果不调用此方法,文件内容为空)
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(writer);
            IoUtil.close(bufferedWriter);
        }
    }
}
