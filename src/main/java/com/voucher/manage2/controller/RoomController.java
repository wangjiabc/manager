package com.voucher.manage2.controller;

import cn.hutool.core.date.DateUtil;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.tools.ObjectUtils;
import com.voucher.manage2.tkmapper.entity.Select;
import com.voucher.manage2.utils.MapUtils;
import com.voucher.sqlserver.context.Connect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    //private ApplicationContext applicationContext = new Connect().get();
    //
    //private CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");

    @Autowired
    private CurrentDao currentDao;

    @RequestMapping("getList")
    public Object getList() {
        Room room = new Room();
        Integer limit = 10;
        room.setLimit(limit);
        room.setOffset(0);
        room.setNotIn("id");
       /* Map<String, Object> query = (Map<String, Object>) jsonMap.get("query");
        String searchContent = query.get("searchContent").toString();
        String state = query.get("state").toString();
        String neaten_flow = query.get("neaten_flow").toString();
        //String[] where = {"state = ", state, "neaten_flow = ", neaten_flow, "address like ", "%" + searchContent + "%"};
        */List<String> searchList = new ArrayList<>();
        /*if (!ObjectUtils.isEmpty(searchContent)) {
            searchList.add("address like");
            searchList.add("%" + searchContent + "%");
        }
        if (!ObjectUtils.isEmpty(state)) {
            searchList.add("state =");
            searchList.add(state);
        }
        if (!ObjectUtils.isEmpty(neaten_flow)) {
            searchList.add("neaten_flow =");
            searchList.add(neaten_flow);
        }*/
        searchList.add("address like");
        searchList.add("%a%");
        searchList.add("num like");
        searchList.add("%c%");
        searchList.add("item_e2c9d7ec9f3af999925c0ce56831801c like");
        searchList.add("%2%");
        searchList.add("del=");
        searchList.add("false");
        String[] where = new String[searchList.size()];
        room.setWhere(searchList.toArray(where));
        room.setWhereTerm("or");
        Map map = null;
        try {
            map = currentDao.selectTable(room);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("updateFieldName")
    public Integer updateTable(String line_uuid, String value) {
        return currentDao.updateTable("item_room", line_uuid, value);
    }

    @RequestMapping("updateRoom")
    public Integer updateItmeTable(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        return currentDao.updateItmeTable(jsonMap);
    }

    @RequestMapping("delRoom")
    public Integer delItmeTable(String guid) {
        return currentDao.delItmeTable(guid);
    }

    //回收逻辑删除
    @RequestMapping("recycleRoom")
    public Integer recycleRoom(@RequestBody List<String> guidList) {
        return currentDao.recycleRoom(guidList);
    }

    //@RequestMapping("addField")
    //public int addField(@RequestParam("title") String fieldName) {
    //    //fieldName = "ccc";
    //    return currentDao.alterTable(true, "item_room", fieldName, null);
    //}
    @RequestMapping("addField")
    public Integer addField(@RequestBody Map<String, Object> jsonMap) {
        Map<Integer, String> selects = null;
        String fieldName = MapUtils.getString("title", jsonMap);
        Integer type = MapUtils.getInteger("type", jsonMap);
        //Map<String, String> selects = (Map<String, String>) jsonMap.get("domains");
        List<LinkedHashMap<String, Object>> domains = (List<LinkedHashMap<String, Object>>) jsonMap.get("domains");
        if (domains != null) {
            selects = new HashMap<>();
            int i = 1;
            for (LinkedHashMap<String, Object> domain : domains) {
                selects.put(i++, MapUtils.getString("value", domain));
            }
        }
        //fieldName = "ccc";
        return currentDao.addField("item_room", fieldName, type, selects);
    }

    @RequestMapping("delField")
    public Integer delField(String line_uuid) {
        //lineUUID = "item_a91c9d5ef10061a004fc1d7a08f27a80";
        //失败返回0
        return currentDao.alterTable(false, "item_room", null, line_uuid);
    }

    @RequestMapping("recycleField")
    public Integer recycleField(String line_uuid) {
        return currentDao.recycleField(line_uuid);
    }

    @RequestMapping("insertResource")
    public Integer insertResource(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        return currentDao.insertResource(jsonMap);
    }

    @RequestMapping("updateSelect")
    public Integer updateSelect(@RequestBody Map<String, Object> jsonMap) {
        //return currentDao.updateSelect();
        System.out.println(jsonMap);
        List<Select> selects = new ArrayList<>();
        currentDao.updateSelect(selects);
        return null;
    }

    @RequestMapping("test")
    public void test() {
        //currentDao.addField("item_room", "aa" + DateUtil.now(), 1, null);
    }
}
