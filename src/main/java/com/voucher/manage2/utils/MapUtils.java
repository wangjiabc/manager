package com.voucher.manage2.utils;

import java.util.Map;

/**
 * @author lz
 * @description 关于map的一些简化操作
 * @date 2019/5/10
 */
public final class MapUtils {

    public static final <K, V> Object getObject(K key, Map<K, V> map) {
        if (ObjectUtils.isEmpty(map))
            return null;
        return map.get(key);
    }

    public static final <K, V> V get(K key, Map<K, V> map) {
        Object object = getObject(key, map);
        V v = object == null ? null : (V) object;
        return v;
    }

    public static final <K, V> String getString(K key, Map<K, V> map) {
        Object object = getObject(key, map);
        V v = object == null ? null : (V) object;
        return v == null ? null : v.toString();
    }

    public static final <K, V> Integer getInteger(K key, Map<K, V> map) {
        return Integer.valueOf(getString(key, map));
    }

    public static final <K, V> Long getLong(K key, Map<K, V> map) {
        return Long.valueOf(getString(key, map));
    }

}
