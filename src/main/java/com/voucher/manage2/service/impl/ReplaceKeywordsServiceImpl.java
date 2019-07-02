package com.voucher.manage2.service.impl;

import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage2.utils.FileUtils;
import com.voucher.manage2.utils.WordTemplateUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReplaceKeywordsServiceImpl implements com.voucher.manage2.service.ReplaceKeywordsService {

    @Override
    public void printWord(ChartInfo chartInfo) throws IOException {
    //public void printWord() throws IOException {
        Map<String, Object> wordDataMap = new HashMap<String, Object>();// 存储全部数据
        Map<String, Object> parametersMap = new HashMap<String, Object>();// 存储不循环的数据

        /*parametersMap.put("ContractNo", "0000001");
        parametersMap.put("ConcludeDate", "626");
        parametersMap.put("Charter", "jack");*/
        //获取数据
        String contractNo = chartInfo.getContractNo();
        Date concludeDate = chartInfo.getConcludeDate();
        //封装到map
        parametersMap.put("ContractNo", contractNo);
        parametersMap.put("ConcludeDate", concludeDate);

        wordDataMap.put("parametersMap", parametersMap);
        File file = new File("D:" + File.separator + "manage-upload"+"\\00.docx");//改成你本地文件所在目录
        //File file = new File("D:/manage-upload/00.docx");//改成你本地文件所在目录

        // 读取word模板
        FileInputStream fileInputStream = new FileInputStream(file);
        WordTemplateUtils template = new WordTemplateUtils(fileInputStream);

        // 替换数据
        template.replaceDocument(wordDataMap);


        //生成文件

        File file1 = new File("D:" + File.separator + "manage-upload"+"\\01.docx");//改成你本地文件所在目录
        //OutputStreamWriter outputFile = new OutputStreamWriter(new FileOutputStream(file1),"GB2312");
        FileOutputStream fos  = new FileOutputStream(file1);
        template.getDocument().write(fos);
    }
}
