package com.voucher.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.voucher.manage2.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.daoModel.ChartRoom;
import com.voucher.manage.daoModel.HireList;
import com.voucher.manage.daoModel.HirePay;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.utils.MapUtils;
import com.voucher.sqlserver.context.Connect;
//@CrossOrigin
@RestController
@RequestMapping("/hireList")
public class HireListController {

    //ApplicationContext applicationContext = new Connect().get();
    //
    //CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");
    @Autowired
    private CurrentDao currentDao;

    @RequestMapping("getChartInfoList")
    public Object getChartInfoList(@RequestBody Map<String, Object> jsonMap) throws ClassNotFoundException {
        ChartInfo chartInfo = new ChartInfo();
        System.out.println("jsonMap=" + jsonMap);
        Integer limit = MapUtils.getInteger("limit", jsonMap);
        Integer offset = MapUtils.getInteger("offset", jsonMap);
        chartInfo.setLimit(limit);
        chartInfo.setOffset(offset);
        chartInfo.setNotIn("id");

        List<String> searchList = new ArrayList<>();

        searchList.add("del=");
        searchList.add("0");
        
        String chartGUID = MapUtils.getString("chartGUID", jsonMap);

        if (chartGUID != null) {
            searchList.add("[ChartInfo].ChartGUID=");
            searchList.add(chartGUID);
        }

        String guid=MapUtils.getString("guid", jsonMap);
        
        if (guid != null) {
            searchList.add("[ChartRoom].guid=");
            searchList.add(guid);
        }
        
        String[] where = new String[searchList.size()];
        if (where.length > 0)
            chartInfo.setWhere(searchList.toArray(where));

        Map map = null;
        try {
            map = currentDao.selectTable(chartInfo, "ChartGUID");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;

    }

    @RequestMapping("getChartInfoRoom")
    public Object getChartInfo(@RequestBody Map<String, Object> jsonMap) throws ClassNotFoundException {

        Integer limit = MapUtils.getInteger("limit", jsonMap);
        Integer offset = MapUtils.getInteger("offset", jsonMap);

        ChartInfo chartInfo = new ChartInfo();
        chartInfo.setLimit(limit);
        chartInfo.setOffset(offset);
        chartInfo.setNotIn("id");

        ChartRoom chartRoom = new ChartRoom();
        chartRoom.setLimit(limit);
        chartRoom.setOffset(offset);
        chartRoom.setNotIn("id");

        List<String> searchList = new ArrayList<>();

        searchList.add("del=");
        searchList.add("0");
        
        String chartGUID = MapUtils.getString("chartGUID", jsonMap);

        if (chartGUID != null) {
            searchList.add("[ChartInfo].ChartGUID=");
            searchList.add(chartGUID);
        }

        String guid=MapUtils.getString("guid", jsonMap);
        
        if (guid != null) {
            searchList.add("[ChartRoom].guid=");
            searchList.add(guid);
        }

        
        String[] where = new String[searchList.size()];
        if (where.length > 0) {
            chartInfo.setWhere(searchList.toArray(where));
            chartRoom.setWhere(searchList.toArray(where));
        }

        Object[] objects = {chartInfo, chartRoom};

        String[][] joinParameters = {{"ChartGUID", "ChartGUID"}};

        String[] itemjoinParameters = {"ChartGUID", "ChartGUID"};

        Map map = null;
        try {
            map = currentDao.selectTable(objects, joinParameters, itemjoinParameters);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;

    }

    @RequestMapping("getHireList")
    public Object getHireList(@RequestBody Map<String, Object> jsonMap) throws ClassNotFoundException {
        Integer limit = MapUtils.getInteger("limit", jsonMap);
        Integer offset = MapUtils.getInteger("offset", jsonMap);
        HireList hireList = new HireList();
        hireList.setLimit(limit);
        hireList.setOffset(offset);
        hireList.setNotIn("id");

        List<String> searchList = new ArrayList<>();

        searchList.add("del=");
        searchList.add("0");
        
        String chartGUID = MapUtils.getString("chartGUID", jsonMap);

        if (chartGUID != null) {
            searchList.add("ChartGUID=");
            searchList.add(chartGUID);
        }

        String[] where = new String[searchList.size()];
        if (where.length > 0) {
            hireList.setWhere(searchList.toArray(where));
        }

        Map map = null;
        try {
            map = currentDao.selectTable(hireList, "HireGUID");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;

    }

    @RequestMapping("getHirePay")
    public Object getHirePay(@RequestBody Map<String, Object> jsonMap) throws ClassNotFoundException {
        Integer limit = MapUtils.getInteger("limit", jsonMap);
        Integer offset = MapUtils.getInteger("offset", jsonMap);
        HirePay hirePay = new HirePay();
        hirePay.setLimit(limit);
        hirePay.setOffset(offset);
        hirePay.setNotIn("id");

        List<String> searchList = new ArrayList<>();

        searchList.add("del=");
        searchList.add("0");
        
        String[] where = new String[searchList.size()];
        if (where.length > 0)
            hirePay.setWhere(searchList.toArray(where));

        Map map = null;
        try {
            map = currentDao.selectTable(hirePay, "HirePayGUID");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;

    }

    @RequestMapping("addField")
    public Integer addField(@RequestParam String tableName, @RequestBody Map<String, Object> jsonMap) {
        Map<Integer, String> selects = null;
        String fieldName = MapUtils.getString("title", jsonMap);
        Integer type = MapUtils.getInteger("type", jsonMap);
        List<LinkedHashMap<String, Object>> domains = (List<LinkedHashMap<String, Object>>) jsonMap.get("domains");
        if (ObjectUtils.isNotEmpty(domains)) {
            selects = new HashMap<>();
            int i = 1;
            for (LinkedHashMap<String, Object> domain : domains) {
                selects.put(i++, MapUtils.getString("value", domain));
            }
        }
        return currentDao.addField(tableName, fieldName, type, selects,null);
    }

    @RequestMapping("delField")
    public Integer delField(@RequestParam String tableName, @RequestParam String lineName, @RequestParam String line_uuid) throws BaseException {
        return currentDao.alterTable(false, tableName, lineName, null, line_uuid);
    }

    @RequestMapping("recycleField")
    public Integer recycleField(String line_uuid) throws BaseException {
        return currentDao.recycleField(line_uuid);
    }

}
