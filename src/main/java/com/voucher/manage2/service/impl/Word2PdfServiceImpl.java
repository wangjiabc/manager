package com.voucher.manage2.service.impl;

import com.voucher.manage2.constant.SystemConstant;
import com.voucher.manage2.service.Word2PdfService;
import com.voucher.manage2.utils.FileUtils;
import com.voucher.manage2.utils.WrodToPDF;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class Word2PdfServiceImpl implements Word2PdfService {
    @Override
    public String convert(String wordFile) {
        //String pdfFileName = Word2pdfUtils.wToPdfChange(wordFile);
        String pdfFileName = WrodToPDF.doc2pdf(wordFile);
        return SystemConstant.PDF_PATH_SUFFIX + File.separator + pdfFileName;
    }
}
