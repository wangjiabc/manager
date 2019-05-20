package com.voucher.manage2.utils;

import com.voucher.manage2.constant.FileConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/5/20
 */
public class FileUtils {
    private static List<String> IMAGE_TYPE = new ArrayList<String>() {
        {
            add("jpg");
            add("bmp");
            add("png");
            add("gif");
            add("jpeg");
        }
    };

    public static boolean isImage(String typeName) {
        return IMAGE_TYPE.contains(typeName.toLowerCase());
    }

    public Integer getFileType(String typeName) {
        String lowerName = typeName.toLowerCase();
        if (ObjectUtils.isEmpty(typeName))
            return -1;
        if (isImage(typeName)) {
            return FileConstant.IMAGE.type;
        }
        FileConstant[] values = FileConstant.values();
        for (FileConstant value : values) {
            if (value.name.equals(typeName.toLowerCase()))
                return value.type;
        }
        return -1;
    }
}
