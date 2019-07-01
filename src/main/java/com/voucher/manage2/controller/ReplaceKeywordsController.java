package com.voucher.manage2.controller;

import com.voucher.manage2.service.ReplaceKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public void acquireData() throws IOException {
        service.printWord();
    }

}
