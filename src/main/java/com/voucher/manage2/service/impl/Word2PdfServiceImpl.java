package com.voucher.manage2.service.impl;
import com.voucher.manage2.service.Word2PdfService;
import com.voucher.manage2.utils.FileUtils;
import com.voucher.manage2.utils.Word2pdfUtils;
import com.voucher.manage2.utils.WrodToPDF;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class Word2PdfServiceImpl implements Word2PdfService {
    @Override
    public String convert(String wordFile) {
        //String pdfFileName = Word2pdfUtils.wToPdfChange(wordFile);
        String pdfFileName = WrodToPDF.doc2pdf(wordFile);
        return FileUtils.SERVER_URL+ File.separator+FileUtils.FILE_URL_PATH_PREFIX+File.separator+pdfFileName;
    }
}
