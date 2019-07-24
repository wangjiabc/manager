package com.voucher.manage2.controller;


import com.alibaba.fastjson.JSONObject;
import com.voucher.manage2.service.ReplaceKeywordsService;
import com.voucher.manage2.service.Word2PdfService;
import com.voucher.manage2.utils.CommonUtils;
import com.voucher.manage2.utils.MapUtils;
import com.voucher.manage2.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * word转PDF文档
 * 返回PDF文档地址
 */
@RestController
@RequestMapping("/convert")
public class Word2PdfController {


    @Autowired
    private Word2PdfService word2PdfService;

    @Autowired
    private ReplaceKeywordsService replaceKeywordsService;

    /**
     * 替换关键字
     *
     * @throws IOException
     */
    @RequestMapping("/acquireData")
    public Object acquireData(@RequestBody JSONObject jsonMap) throws IOException, ParseException {

        //String CardType = MapUtils.getString("CardType", jsonMap);
        String ContractNo = MapUtils.getString("contractNo", jsonMap);
        String ConcludeDate = MapUtils.getString("signDate", jsonMap);
        String Charter = MapUtils.getString("Charter", jsonMap);
        String Address = jsonMap.getString("address");
        String RealEstateNo = "zhengshu";
        String RentArea = MapUtils.getString("rentArea", jsonMap);
        Long BeginDate = jsonMap.getJSONArray("chartDate").getLong(0);
        Long EndDate = jsonMap.getJSONArray("chartDate").getLong(1);

        Double ChartYears = CommonUtils.getDouble(TimeUtils.getMonths(BeginDate, EndDate) / 12);
        String FareItem = jsonMap.getString("hidden");
        String DXRMB = MapUtils.getString("monthRent", jsonMap);
        String DXBZJ = MapUtils.getString("margin", jsonMap);
        HashMap<String, Object> map = new HashMap<>();
        map.put("ContractNo", ContractNo);
        map.put("ConcludeDate", ConcludeDate);
        map.put("Charter", Charter);
        map.put("Address", Address);
        map.put("RealEstateNo", RealEstateNo);
        map.put("RentArea", RentArea);
        map.put("BeginDate", TimeUtils.formatTime(BeginDate, TimeUtils.exp1));
        map.put("EndDate", TimeUtils.formatTime(EndDate, TimeUtils.exp1));
        map.put("ChartYears", ChartYears);
        map.put("FareItem", FareItem);
        map.put("DXRMB", DXRMB);
        map.put("DXBZJ", DXBZJ);


        //替换关键字
        //String replaceWordPath = replaceKeywordsService.printWord(jsonMap);
        String replaceWordPath = replaceKeywordsService.printWord(map);

        //word转PDF方法
        String pdfAdress = word2PdfService.convert(replaceWordPath);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("pdfAdress", pdfAdress);
        return resultMap;
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
