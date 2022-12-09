package com.coolw.code.redis.util;

import cn.hutool.core.collection.CollUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 *
 * @author coolw
 * @date 2022/11/1 16:30
 * @since 1.0
 */
@Slf4j
@UtilityClass
public class FileUtils {

    public void upload(MultipartFile file) {
        if (file == null) {
            return;
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        // 重新生成文件名
        String fName = System.currentTimeMillis() + "_" + fileName;
        String filePath = "D:\\javaspace\\MyProject\\coolw-code\\code-file\\src\\main\\resources\\file\\";
        File dest = new File(filePath + fName);
        try {
            file.transferTo(dest);
            log.info("文件名:[{}]上传成功", fName);
        } catch (IOException e) {
            log.error("文件名:[{}]上传失败", fName, e);
        }
    }

    public void batchUpload(List<MultipartFile> files) {
        if (files.isEmpty()) {
            return;
        }
        files.forEach(FileUtils::upload);
    }

    /**
     * https://blog.csdn.net/lazy_ting/article/details/103716533
     */
    public void downFileZip(HttpServletResponse response) throws IOException {
        // zip包名称
        String zipName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".zip";
        // 返回文件流
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;FileName=" + URLEncoder.encode(zipName, "UTF-8"));

        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        InputStream is;
        // 订单:文件列表
        Map<String, List<File>> listMap = downFiles();
        for (Map.Entry<String, List<File>> entry : listMap.entrySet()) {
            String applyNo = entry.getKey();
            List<File> files = entry.getValue();
            if (CollUtil.isEmpty(files)) {
                continue;
            }
            for (File file : files) {
                // 每一个订单,创建一个文件夹
                ZipEntry zipEntry = new ZipEntry(applyNo + File.separator + file.getName());
                zos.putNextEntry(zipEntry);

                is = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    zos.write(buffer, 0, len);
                }
                is.close();
            }
            zos.flush();
        }
        zos.flush();
        zos.close();
    }

    /**
     * 模拟订单文件列表
     */
    private Map<String, List<File>> downFiles() {
        Map<String, List<File>> filesMap = new HashMap<>();
        File file = new File("D:/deyi/ZYH/img");
        if (file.isDirectory()) {
            List<File> fileList1 = new ArrayList<>();
            List<File> fileList2 = new ArrayList<>();
            List<File> fileList3 = new ArrayList<>();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (i < 2) {
                    fileList1.add(files[i]);
                } else if (i < 7) {
                    fileList2.add(files[i]);
                } else {
                    fileList3.add(files[i]);
                }
            }
            filesMap.put("EP0000" + "_" + 1, fileList1);
            filesMap.put("EP0000" + "_" + 2, fileList2);
            filesMap.put("EP0000" + "_" + 3, fileList3);
        } else {
            List<File> fileList = new ArrayList<>();
            fileList.add(file);
            filesMap.put("EP0000" + "_" + 1, fileList);
        }
        return filesMap;
    }

    /**
     * 文件下载,不创建文件夹
     */
    /*public void downFileZip1(HttpServletResponse response) throws IOException {
        String zipName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".zip";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;FileName=" + URLEncoder.encode(zipName, "UTF-8"));

        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        InputStream is;
        List<File> fileList = downFiles1();
        for (File f : fileList) {
            is = new FileInputStream(f);

            byte[] bytes = fileToByte(f);
            zos.putNextEntry(new ZipEntry(f.getName()));
            zos.write(bytes, 0, bytes.length);
            zos.flush();

            //3.将zip输出
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
                zos.flush();
            }
            is.close();
        }
        zos.flush();
        zos.close();
    }

    private List<File> downFiles1() {
        List<File> fileList = new ArrayList<>();
        File file = new File("D:/deyi/ZYH/img");
        if (file.isDirectory()) {
            Collections.addAll(fileList, file.listFiles());
        } else {
            fileList.add(file);
        }
        return fileList;
    }

    private byte[] fileToByte(File file) {
        byte[] bytes = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            bytes = bos.toByteArray();
        } catch (IOException e) {

        }
        return bytes;
    }*/
}
