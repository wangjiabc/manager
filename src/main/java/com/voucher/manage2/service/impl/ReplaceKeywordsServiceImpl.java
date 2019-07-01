package com.voucher.manage2.service.impl;

import com.voucher.manage2.service.ReplaceKeywordsService;
import com.voucher.manage2.utils.WordTemplateUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReplaceKeywordsServiceImpl implements ReplaceKeywordsService {
    @Test
    @Override
    public void printWord() throws IOException {
        Map<String, Object> wordDataMap = new HashMap<String, Object>();// �洢ȫ������
        Map<String, Object> parametersMap = new HashMap<String, Object>();// �洢��ѭ��������

        parametersMap.put("ContractNo", "0000001");
        parametersMap.put("ConcludeDate", "626");
        parametersMap.put("Charter", "jack");



        wordDataMap.put("parametersMap", parametersMap);
        File file = new File("src\\main\\java\\com\\voucher\\docx\\00.docx");//�ĳ��㱾���ļ�����Ŀ¼


        // ��ȡwordģ��
        FileInputStream fileInputStream = new FileInputStream(file);
        WordTemplateUtils template = new WordTemplateUtils(fileInputStream);

        // �滻����
        template.replaceDocument(wordDataMap);


        //�����ļ�

        File file1 = new File("src\\main\\java\\com\\voucher\\docx\\01.docx");//�ĳ��㱾���ļ�����Ŀ¼
        //OutputStreamWriter outputFile = new OutputStreamWriter(new FileOutputStream(file1),"GB2312");
        FileOutputStream fos  = new FileOutputStream(file1);
        template.getDocument().write(fos);
    }
}
