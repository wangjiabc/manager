package com.voucher.manage2.controller;

import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage2.service.ReplaceKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author z
 * @description 打印word控制器
 * @date 2019/6/26
 */
@RestController
@RequestMapping("/printWord")
public class ReplaceKeywordsController {

    @Autowired
    private ReplaceKeywordsService service;

    /**
     * 替换关键字
     * @throws IOException
     */
    @RequestMapping("/acquireData")
    public void acquireData(@RequestBody ChartInfo chartInfo) throws IOException {
        service.printWord(chartInfo);
    }
    /*public void acquireData(@RequestBody ChartInfo chartInfo,@RequestBody ChartRoom chartRoom) throws IOException {
        service.printWord(chartInfo,chartRoom);
    }*/

}
