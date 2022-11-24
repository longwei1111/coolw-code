package com.coolw.code.redis.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * TODO
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
    
}
