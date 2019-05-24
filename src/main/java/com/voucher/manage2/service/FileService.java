package com.voucher.manage2.service;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author lz
 * @Description: 文件操作
 * @Date: 2019/5/22 15:32
 **/

public interface FileService {

    /**
     * @Author lz
     * @Description: 上传文件
     * @param: file, roomGuids
     * @return: {java.lang.String}
     * @Date: 2019/5/22 15:32
     **/
    String fileUpload(MultipartFile file, List<String> roomGuids,String menuGuid);

}
