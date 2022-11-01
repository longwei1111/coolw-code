package com.coolw.code.thread.word2pdf.controller;

import com.coolw.code.thread.word2pdf.util.BaseResp;
import com.coolw.code.thread.word2pdf.util.FileUploadUtils;
import com.coolw.code.thread.word2pdf.util.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/docpreview")
public class PdfController {

    @Autowired
    private FileUploadUtils fileUploadUtils;
    @Autowired
    private PdfUtils pdfUtils;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public BaseResp<String> upload(@RequestParam MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return BaseResp.fail(-1, "请上传文件");
        }
        String fileName = file.getOriginalFilename();
        String urlFileName = fileUploadUtils.getRandomFileName(fileUploadUtils.getSuffix(fileName));
        String url = fileUploadUtils.upload(file.getBytes(), urlFileName);
        return BaseResp.data(url);
    }

    /**
     * 文件上传并转为PDF
     */
    @PostMapping("/topdf")
    public BaseResp<Map<String, Object>> toPdf(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return BaseResp.fail(-1, "请上传文件");
        }
        String fileName = file.getOriginalFilename();
        String urlFileName = fileUploadUtils.getRandomFileName(fileUploadUtils.getSuffix(fileName));
        String originalUrl = fileUploadUtils.upload(file.getBytes(), urlFileName);
        pdfUtils.toPdf(pdfUtils.getServerPath(originalUrl), pdfUtils.getTargetFolder(originalUrl));
        Map<String, Object> data = new HashMap<>();
        data.put("src", originalUrl);
        data.put("pdfPath", pdfUtils.getPDFUrl(originalUrl));
        return BaseResp.data(data);
    }

    /**
     * 文件上传并转为图片PNG格式
     */
    @PostMapping("/topng")
    public BaseResp<Map<String, Object>> toPng(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return BaseResp.fail(-1, "请上传文件");
        }
        String fileName = file.getOriginalFilename();
        String urlFileName = fileUploadUtils.getRandomFileName(fileUploadUtils.getSuffix(fileName));
        String originalUrl = fileUploadUtils.upload(file.getBytes(), urlFileName);
        pdfUtils.toPdf(pdfUtils.getServerPath(originalUrl), pdfUtils.getTargetFolder(originalUrl));

        int page = pdfUtils.pdf2Image(pdfUtils.getServerPath(pdfUtils.getPDFUrl(originalUrl)), pdfUtils.getTargetFolder(originalUrl), 96);
        Map<String, Object> data = new HashMap<>();
        data.put("src", originalUrl);
        data.put("pdfPath", pdfUtils.getPDFUrl(originalUrl));
        data.put("pngNum", page);
        data.put("pngList", pdfUtils.getPngUrl(originalUrl, page));
        return BaseResp.data(data);
    }

}
