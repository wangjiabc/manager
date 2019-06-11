package com.voucher.manage.dao;

import java.util.List;
import java.util.Map;

public interface ExcelDAO {

	public String insertTable(Map<String, String> jsonMap,List<List<String>> excleLists);
	
}
