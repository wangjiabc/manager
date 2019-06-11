package com.voucher.manage.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.voucher.manage.dao.ExcelDAO;
import com.voucher.manage.tools.MyTestUtil;

public class ExcelDAOImpl extends JdbcDaoSupport implements ExcelDAO{

	@Override
	public String insertTable(Map<String, String> jsonMap, List<List<String>> excelLists) {
		// TODO Auto-generated method stub

		List allTable = new ArrayList<>();

		for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

			String Key = entry.getKey();
			String entrys = entry.getValue();

			List<String> entryList = Arrays.asList(StringUtils.strip(entrys, "[]"));

			String ay = entryList.get(0);

			String[] ayStrings = ay.split("},");

			String REGEX = "}";
			Pattern pattern = Pattern.compile(REGEX);

			List<String> intos = new ArrayList<>();

			List<Object> values = new ArrayList<>();

			for (String ayString : ayStrings) {

				Matcher matcher = pattern.matcher(ayString);
				if (!matcher.find())
					ayString = ayString + "}";

				Map<String, String> rowMap = JSONObject.parseObject(ayString, new TypeReference<Map<String, String>>() {
				});

				if (rowMap.get("type") != null) {

					Map<Integer, Object> map = new HashMap<>();

					if (rowMap.get("type").equals("column")&&rowMap.get("value")!=null&&!rowMap.get("value").equals("")) {

						intos.add(rowMap.get("column"));

						map.put(0, Integer.parseInt(rowMap.get("value")));

						values.add(map);

					} else if (rowMap.get("type").equals("hash")) {

						intos.add(rowMap.get("column"));

						map.put(1, "hash");

						if (rowMap.get("foreignTable")!=null){
							map.put(2, rowMap.get("foreignTable"));							
						}

						map.put(3, rowMap.get("column"));
						
						values.add(map);

					}
				}

			}

			if (intos.size() != 0) {
				Map map = new HashMap<>();
				map.put("tableName", Key);
				map.put("intos", intos);
				map.put("values", values);
				allTable.add(map);
			}

		}

		Iterator<Map<String, Object>> iterator = allTable.iterator();

		Map intoMap=new HashMap<>();
		
		Map hash = new HashMap<>();
		
		while (iterator.hasNext()) {

			Map<String, Object> tableMap = iterator.next();

			String tableName = (String) tableMap.get("tableName");

			List<String> intos = (List) tableMap.get("intos");

			List values = (List) tableMap.get("values");

			Iterator<String> intoIterator = intos.iterator();

			String into = "";

			int i=0;
			
			while (intoIterator.hasNext()) {

				if (into.equals(""))
					into = into + "(";

				String intovalue = intoIterator.next();

				into = into + intovalue + ",";

				Map<Integer, Object> valueMap = (Map<Integer, Object>) values.get(i);

				if (valueMap.get(1) != null && valueMap.get(2) != null) {
					String foreignTable = (String) valueMap.get(2);
					String foreignkey = intovalue;
					Map<String, Object> map = new HashMap<>();
					map.put("oneselfTable", tableName);
					map.put("foreignTable", valueMap.get(2));
					map.put("foreignkey", intovalue);
					Map<String, Object> thistable=(Map) hash.get(tableName);
					if(thistable==null)
						thistable=new HashMap<String, Object>();
					thistable.put(UUID.randomUUID().toString(), map);
					hash.put(tableName, thistable);
				}

				i++;
			}

			into = into.substring(0, into.length() - 1) + ")";

			intoMap.put(tableName, into);
			
		}

		
		for (List<String> excel : excelLists) {

			iterator = allTable.iterator();

			Map<String, Object> outHash=new HashMap<>();
			
			Map<String, Object> inHash=new HashMap<>();
			
			while (iterator.hasNext()) {

				Map<String, Object> tableMap = iterator.next();

				String tableName = (String) tableMap.get("tableName");

				List values = (List) tableMap.get("values");

				Iterator<Map<Integer, Object>> valueIterator = values.iterator();

				String value = "";

				while (valueIterator.hasNext()) {

					if (value.equals(""))
						value = value + "(";

					Map<Integer, Object> valueMap = valueIterator.next();

					if (valueMap.get(0) != null) {
						String v = String.valueOf(valueMap.get(0));
						value = value + "'"+excel.get(Integer.parseInt(v)) + "',";
						
					} else if (valueMap.get(1) != null) {
						String v = UUID.randomUUID().toString();
						value = value + "'" + v + "',";
						Map<String, Object> thisouttable=(Map) outHash.get(tableName);
						if(thisouttable==null)
							thisouttable=new HashMap<String, Object>();
						String column=String.valueOf(valueMap.get(3));
						thisouttable.put(column, v);
						outHash.put(tableName, thisouttable);
						if (valueMap.get(2) != null) {							
							Map<String, Object> keyMap = (Map<String, Object>) hash.get(tableName);
							for (Map.Entry<String, Object> entry : keyMap.entrySet()) {					
								Map<String, Object> thisTable=(Map<String, Object>) entry.getValue();
								Map<String, Object> map=(Map) inHash.get(tableName);
								if(map==null)
									map=new HashMap<String, Object>();
								Map<String, Object> thisintable=new HashMap<>();
								String foreignTable=(String) thisTable.get("foreignTable");
								String foreignkey=(String)thisTable.get("foreignkey");
								String oneselfTable=(String)thisTable.get("oneselfTable");
								thisintable.put("foreignTable", foreignTable);
								thisintable.put("foreignkey", foreignkey);
								thisintable.put("oneselfTable", oneselfTable);								
								String column2=String.valueOf(valueMap.get(3));								
								thisintable.put(column2, v);
								map.put(UUID.randomUUID().toString(), thisintable);
								inHash.put(tableName, map);
							}
						}
					}

				}

				String into = (String) intoMap.get(tableName);

				value = value.substring(0, value.length() - 1) + ")";

				String sql=" INSERT INTO "+tableName+" "+into+" VALUES "+value;
				
				try{
					this.getJdbcTemplate().execute(sql);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return "写入失败";
				}
				
				for (Map.Entry<String, Object> inentry : inHash.entrySet()) {

					String currentHash="";
					
					Map<String, Object> inentryValues = (Map<String, Object>) inentry.getValue();

					for (Map.Entry<String, Object> inentryValue : inentryValues.entrySet()) {

						Map<String, Object> inentr = (Map<String, Object>) inentryValue.getValue();

						String foreignTable = (String) inentr.get("foreignTable");

						for (Map.Entry<String, Object> outentry : outHash.entrySet()) {

							if (outentry.getKey().equals(foreignTable)) {

								Map<String, Object> outMap = (Map<String, Object>) outentry.getValue();

								for (Map.Entry<String, Object> out : outMap.entrySet()) {

									for (Map.Entry<String, Object> in : inentryValues.entrySet()) {

										Map<String, Object> iv = (Map<String, Object>) in.getValue();

										for (Map.Entry<String, Object> i : iv.entrySet()) {

											String thisHash=inentry.getKey()+i.getKey()+out.getValue();

											if(currentHash.equals(thisHash)){
												continue;
											}else {
												currentHash=thisHash;
											}

											if (out.getKey().equals(i.getKey())) {

												String sql2 = " update " + inentry.getKey() + " set " + i.getKey()
														+ " = '" + out.getValue() + "' where " + i.getKey() + " = '"
														+ i.getValue() + "'";

												try{
													this.getJdbcTemplate().execute(sql2);
												}catch (Exception e) {
													// TODO: handle exception
													e.printStackTrace();
													TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
													return "写入失败";
												}
																							
											}
										}
									}
								}
							}
						}
					}
				}
		
				
				
			}
			
		}

		return "写入成功";

	}

}
