package com.voucher.manage.dao;

import java.util.List;

import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.daoModel.ChartRoom;
import com.voucher.manage.daoModel.HireList;
import com.voucher.manage.daoModel.HirePay;
import com.voucher.manage.daoModel.Room;

public interface HireDAO {

	public Room getRoomByGUID(String guid);
	
	public Integer insertHire(ChartInfo chartInfo,List<ChartRoom> chartRooms);
	
	public Integer deleteHire(ChartInfo chartInfo);
	
	public Integer insertHirePay(List<HireList> hireLists);
	
	public Integer deleteHirePay(HirePay hirePay);

	void refundHirePay(List<HirePay> hirePays, List<HireList> hireLists);
}
