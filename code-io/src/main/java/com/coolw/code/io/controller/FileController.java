package com.coolw.code.io.controller;

import com.coolw.code.io.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/11/1 15:39
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public String upload(List<MultipartFile> files) {
        if (files.isEmpty()) {
            return "上传失败，未选择文件";
        }
        FileUtils.batchUpload(files);
        return "文件上传成功";
    }

    /**
     * 下载文件,zip格式
     */
    @PostMapping("/downZip")
    public void downFileZip(HttpServletResponse response) throws IOException {
        FileUtils.downFileZip(response);
    }
}
