package com.voucher.manage2.service.impl;

import com.voucher.manage2.service.Word2PdfService;
import com.voucher.manage2.utils.Word2pdfUtils;
import org.springframework.stereotype.Service;

@Service
public class Word2PdfServiceImpl implements Word2PdfService {
    @Override
    public void convert(String wordFile,String pdfFile) {
        Word2pdfUtils.wToPdfChange(wordFile,pdfFile);
    }
}
