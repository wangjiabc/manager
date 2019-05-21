package com.voucher.manage2.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.msg.Message;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author lz
 * @description 文件控制器
 * @date 2019/5/20
 */
@RestController
@RequestMapping("file")
public class FileController {
    @PostMapping("upload")
    public List<String> springUpload(HttpServletRequest request) {
        //1文件上传
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            String realPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "upload";
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {

                }
            }

        }
        return null;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam("filename") String filename) throws IOException {
        //从我们的上传文件夹中去取
        //String downloadFilePath = "D:\\userUploadFile\\Files";
        //新建一个文件
        File file = new File(filename);
        //http头信息
        HttpHeaders headers = new HttpHeaders();
        //设置编码
        //String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");

        headers.setContentDispositionFormData("attachment", filename);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

    }
}
