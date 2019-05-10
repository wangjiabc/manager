package com.voucher.manage.dao;

import com.voucher.manage2.tkmapper.entity.Select;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface CurrentDao {

    void createTable(String tableName);

    int alterTable(Boolean addOrDel, String tableName, String lineName, String lineUuid);

    Integer existTable(String tableName);

    Map selectTable(Object object) throws ClassNotFoundException;

    Integer insertTable(Object object, String val) throws ClassNotFoundException;

    Integer updateTable(String tableName, String line_uuid, String value);

    Integer updateItmeTable(Map<String, Object> roomMap) throws InvocationTargetException, IllegalAccessException;

    Integer delItmeTable(String guid);

    Integer recycleRoom(List<String> guidList);

    Integer insertResource(Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException;

    Integer recycleField(String line_uuid);

    Integer addField(String tableName, String fieldName, Integer type, Map<Integer,String> selectValue);

    Integer updateSelect(List<Select> selects);
}
