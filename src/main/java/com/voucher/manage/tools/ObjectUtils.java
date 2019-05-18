package com.voucher.manage.tools;

import java.lang.reflect.Array;
import java.util.*;

public class ObjectUtils extends org.springframework.util.ObjectUtils {


    /**
     * @Author lz
     * @Description: 只要有一个为enmpty
     * @param: [objs]
     * @return: {boolean}
     * @Date: 2019/5/13 11:25
     **/
    public static boolean isEmpty(Object... objs) {
        boolean isEmpty = false;
        for (Object o : objs) {
            if (o == null) {
                isEmpty = true;
            } else if (o.getClass().isArray()) {
                isEmpty = Array.getLength(o) == 0;
            } else if (o instanceof CharSequence) {
                isEmpty = ((CharSequence) o).length() == 0;
            } else if (o instanceof Collection) {
                isEmpty = ((Collection) o).isEmpty();
            } else if (o instanceof Map) {
                isEmpty = ((Map) o).isEmpty();
            } else {
                String s = o.toString();
                isEmpty = "null".equals(s) || "".equals(s);
            }
            if (isEmpty)
                return isEmpty;
        }
        return isEmpty;
    }

    public static boolean isNotEmpty(Object... objs) {
        return !isEmpty(objs);
    }
}
