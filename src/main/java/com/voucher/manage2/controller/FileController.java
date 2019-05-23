package com.voucher.manage2.controller;

import com.google.common.collect.Lists;
import com.voucher.manage2.exception.FileUploadException;
import com.voucher.manage2.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author lz
 * @description 文件控制器
 * @date 2019/5/20
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public void springUpload(HttpServletRequest request, String[] roomGuids) {
        List<String> fileNames = new ArrayList<>();
        //List<String> failedFileNames = new ArrayList<>();
        //List<String> roomGuids = (List<String>) jsonMap.get("roomGuids");
        //1文件上传
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    //try {
                    fileNames.add(fileService.fileUpload(file, Lists.newArrayList(roomGuids)));
                    //} catch (FileUploadException e) {
                    //    failedFileNames.add(e.getMessage());
                    //}
                }
            }
        }
        //HashMap<String, List<String>> resultMap = new HashMap<>(4);
        //resultMap.put("success", fileNames);
        //resultMap.put("failed", failedFileNames);
        //return resultMap;
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(String fileName) throws IOException {
        //从我们的上传文件夹中去取
        //String downloadFilePath = "D:\\userUploadFile\\Files";
        //新建一个文件
        File file = new File(com.voucher.manage2.utils.FileUtils.getFilePath(fileName) + File.separator + fileName);
        //http头信息
        HttpHeaders headers = new HttpHeaders();
        //设置编码
        String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");

        headers.setContentDispositionFormData("attachment", downloadFileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

    }
}
