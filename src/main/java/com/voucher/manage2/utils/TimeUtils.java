package com.voucher.manage2.utils;

import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author lz
 * @description 时间工具类
 * @date 2019/6/6
 */
public class TimeUtils extends DateUtil {
    public final static String exp1 = "yyyy-MM-dd";
    public final static String exp2 = "yyyy-MM-dd HH:mm:ss";

    public static String formatTime(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }
}
