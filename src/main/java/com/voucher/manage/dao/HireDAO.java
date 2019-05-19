package com.voucher.manage.dao;

import java.util.List;

import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.daoModel.ChartRoom;
import com.voucher.manage.daoModel.Room;

public interface HireDAO {

	public Room getRoomByGUID(String guid);
	
	public Integer insertHire(ChartInfo chartInfo,List<ChartRoom> chartRooms);
	
}
