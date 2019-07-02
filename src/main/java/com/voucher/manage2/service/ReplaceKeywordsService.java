package com.voucher.manage2.service;

import org.springframework.stereotype.Service;

import java.io.IOException;


public interface ReplaceKeywordsService {

    /**
     * 对docx文件中的文本及表格中的内容进行替换
     */
    public void printWord() throws IOException;

}
