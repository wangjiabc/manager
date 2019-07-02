package com.voucher.manage2.service.impl;

import com.voucher.manage2.service.PdfAddressRuturnService;
import org.springframework.stereotype.Service;


@Service
public class PdfAddressRuturnServiceImpl implements PdfAddressRuturnService {
    @Override
    public String ruturnAddress() {
        return "src\\main\\java\\com\\voucher\\docx\\02.pdf";
    }
}
