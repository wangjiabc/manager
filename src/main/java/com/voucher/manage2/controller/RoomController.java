package com.voucher.manage2.controller;

import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.tools.ObjectUtils;
import com.voucher.sqlserver.context.Connect;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {

    private ApplicationContext applicationContext = new Connect().get();

    private CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");

    @RequestMapping("getList")
    public Object getList(@RequestBody Map<String, Object> jsonMap) {
        Room room = new Room();
        Integer limit = Integer.valueOf(jsonMap.get("limit").toString());
        room.setLimit(limit);
        room.setOffset((Integer.valueOf(jsonMap.get("page").toString()) - 1) * limit);
        room.setNotIn("id");
        Map<String, Object> query = (Map<String, Object>) jsonMap.get("query");
        String searchContent = query.get("searchContent").toString();
        String state = query.get("state").toString();
        String neaten_flow = query.get("neaten_flow").toString();
        //String[] where = {"state = ", state, "neaten_flow = ", neaten_flow, "address like ", "%" + searchContent + "%"};
        List<String> searchList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(searchContent)) {
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
        }
        searchList.add("del=");
        searchList.add("false");
        String[] where = new String[searchList.size()];
        room.setWhere(searchList.toArray(where));
        //room.setWhereTerm();
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

    @RequestMapping("addField")
    public int addField(@RequestParam("title") String fieldName) {
        //fieldName = "ccc";
        return currentDao.alterTable(true, "item_room", fieldName, null);
    }

    @RequestMapping("delField")
    public int delField(String line_uuid) {
        //lineUUID = "item_a91c9d5ef10061a004fc1d7a08f27a80";
        //失败返回0
        return currentDao.alterTable(false, "item_room", null, line_uuid);
    }

    @RequestMapping("recycleField")
    public int recycleField(String line_uuid) {
        return currentDao.recycleField(line_uuid);
    }

    @RequestMapping("insertResource")
    public int insertResource(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        return currentDao.insertResource(jsonMap);
    }
}
