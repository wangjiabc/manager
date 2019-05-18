package com.voucher.manage.dao;

import com.voucher.manage.daoModel.Room;

import java.util.List;
import java.util.Map;

public interface AssetsDAO {

	public Map<String, Object> getAllChartInfo(Integer limit, Integer offset, String sort,
			String order,Map search);

	public List getRoom(Room room);

}
