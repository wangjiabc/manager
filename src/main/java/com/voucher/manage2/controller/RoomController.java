package com.voucher.manage2.controller;

import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage2.utils.ObjectUtils;
import com.voucher.manage2.constant.ResultConstant;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.tkmapper.entity.Select;
import com.voucher.manage2.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object getList(@RequestBody Map<String, Object> jsonMap) throws ClassNotFoundException {
        Room room = new Room();
        Integer limit = MapUtils.getInteger("limit", jsonMap);
        room.setLimit(limit);
        room.setOffset(((MapUtils.getInteger("page", jsonMap)) - 1) * limit);
        room.setNotIn("id");
        Map<String, Object> query = (Map<String, Object>) jsonMap.get("query");
        String searchContent = query.get("searchContent").toString();
        String state = query.get("state").toString();
        String neaten_flow = query.get("neaten_flow").toString();
        //String[] where = {"state = ", state, "neaten_flow = ", neaten_flow, "address like ", "%" + searchContent + "%"};
        List<String> searchList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(searchContent)) {
            searchList.add("address like");
            searchList.add("%" + searchContent + "%");
        }
        if (ObjectUtils.isNotEmpty(state)) {
            searchList.add("state =");
            searchList.add(state);
        }
        if (ObjectUtils.isNotEmpty(neaten_flow)) {
            searchList.add("neaten_flow =");
            searchList.add(neaten_flow);
        }
        searchList.add("del=");
        searchList.add("false");
        String[] where = new String[searchList.size()];
        room.setWhere(searchList.toArray(where));
        room.setWhereTerm("or");
        return currentDao.selectTable(room);
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
        if (ObjectUtils.isNotEmpty(domains)) {
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
    public Integer delField(String line_uuid) throws BaseException {
        return currentDao.alterTable(false, "item_room", null, line_uuid);
    }

    @RequestMapping("recycleField")
    public Integer recycleField(String line_uuid) throws BaseException {
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
        Map<String, String> domains = (Map<String, String>) jsonMap.get("domains");
        String line_uuid = MapUtils.getString("line_uuid", jsonMap);
        if (ObjectUtils.isEmpty(domains, line_uuid))
            return ResultConstant.FAILD;
        List<Select> selects = new ArrayList<>();
        for (Map.Entry<String, String> entry : domains.entrySet()) {
            Select select = new Select();
            select.setLineUuid(line_uuid);
            select.setValue(Integer.valueOf(entry.getKey()));
            select.setName(entry.getValue());
            selects.add(select);
        }
        return currentDao.updateSelect(selects);
    }

    @RequestMapping("updateTextLength")
    public Integer updateTextLength(@RequestBody Map<String, Object> jsonMap) {
        String line_uuid = MapUtils.getString("line_uuid", jsonMap);
        Integer text_length = MapUtils.getInteger("text_length", jsonMap);
        if (ObjectUtils.isEmpty(line_uuid, text_length))
            return ResultConstant.FAILD;
        return currentDao.updateTextLength("item_room", line_uuid, text_length);
    }

    @RequestMapping("test")
    public void test() {

        //currentDao.addField("item_room", "aa" + DateUtil.now(), 1, null);
    }
}
