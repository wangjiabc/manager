package com.voucher.manage2.service;


import org.springframework.stereotype.Service;

/**
 * word to PDF
 */

public interface Word2PdfService {
    void convert(String wordFile, String pdfFile);
}
