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
    /* 所有文件绝对路径*/
    private static final String FILE_PATH = "D:" + File.separator + "voucher-upload";
    /*url获取文件的虚拟路径*/
    private static final String FILE_URL_PATH_PREFIX = "file";
    private static final List<String> IMAGE_TYPE = Lists.newArrayList("jpg", "bmp", "png", "jpeg");

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

    /**
     * @Author lz
     * @Description:获取文件对象名,例 D:\voucher-upload\05.jpg
     * @param: [fileName]
     * @return: {java.lang.String}
     * @Date: 2019/5/23 14:23
     **/
    public static File getFileByFileName(String fileName) {
        String filePath = FILE_PATH + File.separator
                + getFileTypeName(getFileType(fileName.substring(fileName.lastIndexOf(".") + 1)));
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(filePath + File.separator + fileName);
    }

    public static String getFileUrlPath(String fileName) {
        return FILE_URL_PATH_PREFIX + File.separator + fileName;
    }

}
