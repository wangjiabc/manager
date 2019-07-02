package com.voucher.manage2.controller;

import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.daoModel.ChartRoom;
import com.voucher.manage2.service.ReplaceKeywordsService;
import com.voucher.manage2.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author z
 * @description ´òÓ¡word¿ØÖÆÆ÷
 * @date 2019/6/26
 */
@RestController
@RequestMapping("/printWord")
public class ReplaceKeywordsController {

    @Autowired
    private ReplaceKeywordsService service;

    /**
     * Ìæ»»¹Ø¼ü×Ö
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
