package com.coolw.code.thread.file.controller;

import com.coolw.code.thread.file.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/test")
    public String upload() {
        return "旭东,调通了";
    }

    @PostMapping("/upload")
    public String upload(List<MultipartFile> files) {
        if (files.isEmpty()) {
            return "上传失败，未选择文件";
        }
        FileUtils.batchUpload(files);
        return "文件上传成功";
    }
    
}
