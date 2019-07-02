package com.voucher.manage2.controller;


import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage2.constant.SystemConstant;
import com.voucher.manage2.service.ReplaceKeywordsService;
import com.voucher.manage2.service.Word2PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;


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
    public void acquireData(@RequestBody ChartInfo chartInfo) throws IOException {
        //替换关键字
        service.printWord(chartInfo);

        //word转PDF方法
        //String word = "src\\main\\java\\com\\voucher\\docx\\01.docx";
        String word = "D:" + File.separator + "manage-upload"+"\\01.docx";
        //String name = "02".concat(".pdf");
        //String pdf = "src\\main\\java\\com\\voucher\\docx\\02.pdf";
        String pdf = "D:" + File.separator + "manage-upload"+"\\02.pdf";
        word2PdfService.convert(word,pdf);
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
    @RequestMapping("/return")
    public String returnAddress(){
        return SystemConstant.PDF_ADDRESS;
    }
}
