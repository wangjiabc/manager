package com.voucher.manage2.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lz
 * @Date: 2019/5/21 10:14
 **/

public enum FileConstant {

    //文件的大类型
    OTHER(0, "其他"),
    IMAGE(1, "图片"),
    EXCEL(2, "excel"),
    WORD(3, "word"),
    PDF(4, "pdf");

    public Integer type;
    public String typeName;

    FileConstant(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static final Map<Integer, String> FILE_TYPE_MAP = new HashMap<Integer, String>() {
        {
            for (FileConstant file : FileConstant.values()) {
                put(file.type, file.typeName);
            }
        }
    };

}
