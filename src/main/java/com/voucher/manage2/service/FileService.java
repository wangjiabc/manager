package com.voucher.manage2.service;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    String fileUpload( MultipartFile file, List<String> roomGuids);
}
