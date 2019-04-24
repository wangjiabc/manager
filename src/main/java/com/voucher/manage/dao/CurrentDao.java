package com.voucher.manage.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface CurrentDao {

	public void createTable(String tableName);
	
	public int alterTable(Boolean addOrDel, String tableName , String lineName, String lineUuid);
	
	public Integer existTable(String tableName);
	
	public Map selectTable(Object object)throws ClassNotFoundException;
	
	public Integer insertTable(Object object,String val)throws ClassNotFoundException;

	Integer updateTable(String tableName, String line_uuid, String value);

    Integer updateItmeTable(Map<String, Object> roomMap) throws InvocationTargetException, IllegalAccessException;

	Integer delItmeTable(String guid);

	Integer recycleRoom(List<String> guidList);

	Integer insertResource(Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException;

    Integer recycleField(String line_uuid);
}
