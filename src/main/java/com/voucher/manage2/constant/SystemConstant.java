package com.voucher.manage2.constant;

import com.voucher.manage2.utils.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author lz
 * @description
 * @date 2019/6/19
 */
@Component
public class SystemConstant implements InitializingBean {
    public static final String SYSTEM_ROLE_GUID = "07daf7bc5c7d41f7b5b1291d3f275d17";

    public static final String START_WORD_PATH = FileUtils.FILE_PATH+File.separator+"cache";
    public static final String START_WORD_FILENAME = "00.docx";
    public static final String WORD_SUFFIX = ".docx";
    public static final String PDF_SUFFIX = ".pdf";

    @Override
    public void afterPropertiesSet() throws Exception {
        File file = new File(START_WORD_PATH);
        if (!file.exists()){
            file.mkdirs();
        }
    }

//    public static final String REPLACE_WORD = "D:" + File.separator + "manage-upload"+"\\01.docx";
//    public static final String PDF_ADDRESS = "D:" + File.separator + "manage-upload"+"\\02.pdf";

}
