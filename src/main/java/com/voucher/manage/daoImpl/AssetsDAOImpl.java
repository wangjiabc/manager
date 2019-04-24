package com.voucher.manage.daoImpl;

import com.voucher.manage.dao.AssetsDAO;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.daoModel.TTT.ChartInfo;
import com.voucher.manage.daoSQL.SelectExe;
import com.voucher.manage.tools.TransMapToString;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetsDAOImpl extends JdbcDaoSupport implements AssetsDAO{

	@Override
	public Map<String, Object> getAllChartInfo(Integer limit, Integer offset, String sort, String order, Map search) {
		// TODO Auto-generated method stub
		ChartInfo chartInfo=new ChartInfo();
		
		chartInfo.setLimit(limit);
		chartInfo.setOffset(offset);
		if(sort!=null&&!sort.equals("")){
			chartInfo.setSort(sort);
		}
		if(offset!=null&&!offset.equals("")){
			chartInfo.setOffset(offset);
		}
		
		chartInfo.setNotIn("GUID");
		
		if(!search.isEmpty()){
			String[] where=TransMapToString.get(search);
			chartInfo.setWhere(where);
		}
		
		Map<String, Object> map = new HashMap<>();

		List list = SelectExe.get(this.getJdbcTemplate(), chartInfo);
		map.put("rows", list);

		Map countMap = SelectExe.getCount(this.getJdbcTemplate(), chartInfo);

		map.put("total", countMap.get(""));
		
		return map;
	}


	@Override
	public List getRoom(Room room) {
		return SelectExe.get(this.getJdbcTemplate(),room);
	}
	
	
}
