package com.voucher.manage.tools;

import java.lang.reflect.Array;
import java.util.*;

public class ObjectUtils extends org.springframework.util.ObjectUtils {

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else {
            String s = obj.toString();
            return "null".equals(s) || "".equals(s);
        }

    }
}
