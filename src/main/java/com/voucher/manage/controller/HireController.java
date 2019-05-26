package com.voucher.manage.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.voucher.manage.dao.HiddenDAO;
import org.apache.regexp.recompile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.dao.HireDAO;
import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.daoModel.ChartRoom;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.tools.MonthDiff;
import com.voucher.manage2.utils.MapUtils;
import com.voucher.sqlserver.context.Connect;

@RestController
@RequestMapping("/hire")
public class HireController {

    //ApplicationContext applicationContext = new Connect().get();
    //
    //CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");
    @Autowired
    private CurrentDao currentDao;
    //
    //HireDAO hireDao = (HireDAO) applicationContext.getBean("hireDao");
    @Autowired
    private HireDAO hireDao;

    @RequestMapping("/action")
    public Object action(@RequestBody Map<String, Object> jsonMap) throws ClassNotFoundException, ParseException {

        String contractNo = MapUtils.getString("contractNo", jsonMap);

        String guidString = MapUtils.getString("guids", jsonMap);

        List guids = JSONArray.parseArray(guidString);

        String hiresString = MapUtils.getString("hires", jsonMap);

        List hires = new ArrayList<>();

        try {
            hires = JSONArray.parseArray(hiresString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        String chartAreaString = MapUtils.getString("chartAreas", jsonMap);

        List chartAreas = new ArrayList<>();

        try {
            chartAreas = JSONArray.parseArray(chartAreaString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        String Charter = MapUtils.getString("Charter", jsonMap);

        int sex = MapUtils.getString("sex", jsonMap).equals("男") ? 1 : 0;

        String CardType = MapUtils.getString("CardType", jsonMap);

        String IDNo = MapUtils.getString("IDNo", jsonMap);

        String Phone = MapUtils.getString("Phone", jsonMap);
        
        
        int month = 0;
        
        Date chartBeginDate=null;
        
        Date chartEndDate=null;
        
        try{
        	month=MapUtils.getInteger("month", jsonMap);
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        
        try{
        	String sDate=MapUtils.getString("chartBeginDate", jsonMap);
        	String eDate=MapUtils.getString("chartEndDate", jsonMap);
        	
        	DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	
        	chartBeginDate=format.parse(sDate);
        	chartEndDate=format.parse(eDate);
        	
        	month=MonthDiff.get(chartBeginDate, chartEndDate);
        	
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        
        Integer augment = null;

        Float increment = null;

        Integer augmentGenre = null;

        String augmentDate = null;

        try {

            augment = MapUtils.getInteger("augment", jsonMap);
            increment = Float.valueOf(MapUtils.getString("increment", jsonMap));
            augmentGenre = MapUtils.getInteger("augmentGenre", jsonMap);
            augmentDate = MapUtils.getString("augmentDate", jsonMap);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        Date date = new Date();

        String ChartGUID = UUID.randomUUID().toString();

        ChartInfo chartInfo = new ChartInfo();

        chartInfo.setChartGUID(ChartGUID);
        chartInfo.setContractNo(contractNo);
		if (chartBeginDate!= null) {
			chartInfo.setConcludeDate(chartBeginDate);
			chartInfo.setChartBeginDate(chartBeginDate);
		}else{
			chartInfo.setConcludeDate(date);
			chartInfo.setChartBeginDate(date);
		}
		if(chartEndDate!=null){
			chartInfo.setChartEndDate(chartEndDate);
		}
		
        chartInfo.setChartMothon(month);
        chartInfo.setIsHistory(0);

        if (augment != null) {
            chartInfo.setAugment(augment);
            chartInfo.setIncrement(increment);
            if (augmentGenre != null) {
                chartInfo.setAugmentGenre(augmentGenre);
                DateFormat fmt = new SimpleDateFormat("yyyy-MM");
                Date augmentTime = fmt.parse(augmentDate);
                chartInfo.setAugmentDate(augmentTime);
            } else {
                chartInfo.setAugmentGenre(0);
            }
        }

        List chartRooms = new ArrayList<>();

        Iterator<String> iterator = guids.iterator();

        Iterator<String> iterator2 = hires.iterator();

        Iterator<String> iterator3 = chartAreas.iterator();

        float allChartAreas = 0;

        float allHires = 0;

        while (iterator.hasNext()) {

            String guid = iterator.next();

            ChartRoom chartRoom = new ChartRoom();

            chartRoom.setChartGUID(ChartGUID);
            chartRoom.setGuid(guid);
            chartRoom.setCharter(Charter);
            chartRoom.setSex(sex);
            chartRoom.setCardType(CardType);
            chartRoom.setIDNo(IDNo);
            chartRoom.setPhone(Phone);

            if (iterator2.hasNext()) {

                float hire = Float.valueOf(iterator2.next());

                allHires = allHires + hire;

                chartRoom.setHire(hire);

            }

            if (iterator3.hasNext()) {

                float chartArea = Float.valueOf(iterator3.next());

                chartRoom.setChartArea(chartArea);

                allChartAreas = allChartAreas + chartArea;

            } else {

                Room room = hireDao.getRoomByGUID(guid);

                chartRoom.setChartArea(room.getBuild_area());

                allChartAreas = allChartAreas + room.getBuild_area();

            }


            chartRooms.add(chartRoom);

        }

        chartInfo.setAllHire(allHires);
        chartInfo.setAllChartArea(allChartAreas);

        return hireDao.insertHire(chartInfo, chartRooms);

    }

}
