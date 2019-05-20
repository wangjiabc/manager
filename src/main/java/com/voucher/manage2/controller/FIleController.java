package com.voucher.manage2.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.msg.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author lz
 * @description 文件控制器
 * @date 2019/5/20
 */
@RestController
@RequestMapping("file")
public class FIleController {
    @PostMapping("upload")
    public void springUpload(HttpServletRequest request) {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            String realPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "upload" + File.separator;
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            try {
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    String type = FileTypeUtil.getType(file.getInputStream());
                    if (file != null) {
                        String path = realPath + file.getOriginalFilename();
                        //上传
                        System.out.println("+++++++++" + path);
                        file.transferTo(new File(path));

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BaseException(Message.FILE_UPLOAD_FAILED, e);
            }

        }
    }
}
