package com.voucher.manage2.controller;


import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.tools.MonthDiff;
import com.voucher.manage2.constant.SystemConstant;
import com.voucher.manage2.service.ReplaceKeywordsService;
import com.voucher.manage2.service.Word2PdfService;
import com.voucher.manage2.utils.MapUtils;
import com.voucher.manage2.utils.TestPDFBinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * word转PDF文档
 * 返回PDF文档地址
 */
@Controller
@RequestMapping("/convert")
public class Word2PdfController {


    @Autowired
    private Word2PdfService word2PdfService;

    @Autowired
    private ReplaceKeywordsService service;

    /**
     * 替换关键字
     * @throws IOException
     */
    @RequestMapping("/acquireData")
    public String acquireData(@RequestBody Map<String, Object> jsonMap) throws IOException {

        //替换关键字
        String replaceWordPath = service.printWord(jsonMap);

        //word转PDF方法

        return word2PdfService.convert(replaceWordPath);
    }

    /**
     * word转PDF方法
     */
/*    @RequestMapping("/w2p")
    public void word2Pdf(){
        String word = "src\\main\\java\\com\\voucher\\docx\\01.docx";
        //String name = "02".concat(".pdf");
        String pdf = "src\\main\\java\\com\\voucher\\docx\\02.pdf";
        word2PdfService.convert(word,pdf);
    }*/

    /**
     * 返回PDF地址
     * @return
     */
//    @RequestMapping("/return")
//    public String returnAddress(){
//        //返回地址
//        return SystemConstant.PDF_ADDRESS;
//        //将PDF格式文件转成base64编码 
////        String path = "D:" + File.separator + "manage-upload"+"\\02.pdf";
////        String base64String = TestPDFBinary.getPDFBinary(new File(path));
////        System.out.println(base64String);
////        return  base64String;
//    }
}
