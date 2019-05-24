package com.voucher.manage2.controller;

import com.google.common.collect.Lists;
import com.voucher.manage2.exception.FileUploadException;
import com.voucher.manage2.service.FileService;
import com.voucher.manage2.tkmapper.entity.Menu;
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
    @Autowired
    private CommonsMultipartResolver multipartResolver;

    @PostMapping("upload")
    public void springUpload(HttpServletRequest request, String[] roomGuids, String menuGuid) {
        List<String> fileNames = new ArrayList<>();
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
                    fileNames.add(fileService.fileUpload(file, Lists.newArrayList(roomGuids), menuGuid));
                }
            }
        }
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(String fileName) throws IOException {
        //从我们的上传文件夹中去取
        //String downloadFilePath = "D:\\userUploadFile\\Files";
        //新建一个文件
        File file = com.voucher.manage2.utils.FileUtils.getFileByFileName(fileName);
        //http头信息
        HttpHeaders headers = new HttpHeaders();
        //设置编码,下载的时的文件名
        String downloadFileName = new String(fileName.substring(fileName.lastIndexOf("_") + 1).getBytes("UTF-8"), "iso-8859-1");

        headers.setContentDispositionFormData("attachment", downloadFileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

    }


}
