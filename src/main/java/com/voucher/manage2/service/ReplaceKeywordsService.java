package com.voucher.manage2.service;

import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.daoModel.ChartRoom;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

public interface ReplaceKeywordsService {

    /**
     * 对docx文件中的文本及表格中的内容进行替换
     */
    public void printWord(ChartInfo chartInfo) throws IOException;
    //public void printWord() throws IOException;

}
