package com.voucher.manage2.utils;

import com.google.common.collect.Lists;
import com.voucher.manage2.constant.FileConstant;

import java.io.File;
import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/5/20
 */
public class FileUtils {
    private static List<String> IMAGE_TYPE = Lists.newArrayList("jpg", "bmp", "png", "jpeg");

    public static boolean isImage(String suffixName) {
        return IMAGE_TYPE.contains(suffixName);
    }

    public static Integer getFileType(String suffixName) {
        String lowerName = suffixName.toLowerCase();
        if (ObjectUtils.isEmpty(suffixName)) {
            return -1;
        }
        if (isImage(lowerName)) {
            return FileConstant.IMAGE.type;
        }
        if (isExcel(lowerName)) {
            return FileConstant.EXCEL.type;
        }
        if (isWord(lowerName)) {
            return FileConstant.WORD.type;
        }
        if (isPdf(lowerName)) {
            return FileConstant.PDF.type;
        }

        return FileConstant.OTHER.type;
    }

    private static boolean isExcel(String suffixName) {
        return Lists.newArrayList("xls", "xlxs").contains(suffixName);
    }

    private static boolean isWord(String suffixName) {
        return Lists.newArrayList("docx", "doc").contains(suffixName);
    }

    private static boolean isPdf(String suffixName) {
        return Lists.newArrayList("pdf").contains(suffixName);
    }

    public static String getFileTypeName(Integer type) {
        return FileConstant.FILE_TYPE_MAP.get(type);
    }

    public static String getFilePath(Integer fileType) {
        return SpringUtils.getProjectRealPath()+"-upload" + File.separator + getFileTypeName(fileType);
    }

    public static String getFilePath(String fileName) {
        return SpringUtils.getProjectRealPath()+"-upload" + File.separator
                + getFileTypeName(getFileType(fileName.substring(fileName.lastIndexOf(".") + 1)));
    }

}
