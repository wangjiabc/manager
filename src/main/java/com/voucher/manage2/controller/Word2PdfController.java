package com.voucher.manage2.controller;


import com.voucher.manage2.service.PdfAddressRuturnService;
import com.voucher.manage2.service.Word2PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * word转PDF文档
 * 返回PDF文档地址
 */
@Controller
@RequestMapping("/convert")
public class Word2PdfController {
    @Autowired
    private PdfAddressRuturnService  ruturnService;

    @Autowired
    private Word2PdfService word2PdfService;


    /**
     * word转PDF方法
     */
    @RequestMapping("/w2p")
    public void word2Pdf(){
        String word = "src\\main\\java\\com\\voucher\\docx\\01.docx";
        String name = "02".concat(".pdf");
        String pdf = "src\\main\\java\\com\\voucher\\docx\\"+name;
        word2PdfService.convert(word,pdf);
    }

    /**
     * 返回PDF地址
     * @return
     */
    @RequestMapping("/return")
    public String returnAddress(){
        return ruturnService.ruturnAddress();
    }
}
