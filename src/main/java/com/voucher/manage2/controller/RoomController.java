package com.voucher.manage2.controller;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage2.service.RoomService;
import com.voucher.manage2.tkmapper.entity.RoomIn;
import com.voucher.manage2.tkmapper.entity.RoomOut;
import com.voucher.manage2.utils.ObjectUtils;
import com.voucher.manage2.constant.ResultConstant;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.tkmapper.entity.Select;
import com.voucher.manage2.utils.MapUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/room")
public class RoomController {


    @Autowired
    private CurrentDao currentDao;
    @Autowired
    private RoomService roomService;

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
        return currentDao.selectTable(room, "guid");
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


    @RequestMapping("recycleRoom")
    public Integer recycleRoom(@RequestBody List<String> guidList) {
        //回收逻辑删除
        return currentDao.recycleRoom(guidList);
    }

    @RequestMapping("addField")
    public Integer addField(@RequestBody Map<String, Object> jsonMap) {
        Map<Integer, String> selects = null;
        String fieldName = MapUtils.getString("title", jsonMap);
        Integer roomType = MapUtils.getInteger("roomType", jsonMap);
        Integer filedType = MapUtils.getInteger("fieldType", jsonMap);
        List<LinkedHashMap<String, Object>> domains = (List<LinkedHashMap<String, Object>>) jsonMap.get("domains");
        if (ObjectUtils.isNotEmpty(domains)) {
            selects = new HashMap<>();
            int i = 1;
            for (LinkedHashMap<String, Object> domain : domains) {
                selects.put(i++, MapUtils.getString("value", domain));
            }
        }
        //fieldName = "ccc";
        return currentDao.addField("item_room", fieldName, filedType, selects, roomType);
    }

    @RequestMapping("delField")
    public Integer delField(String line_uuid) throws BaseException {
        return currentDao.alterTable(false, "item_room", "guid", null, line_uuid);
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
        System.out.println(jsonMap);
        Map<String, String> domains = (Map<String, String>) jsonMap.get("domains");
        String line_uuid = MapUtils.getString("line_uuid", jsonMap);
        if (ObjectUtils.isEmpty(domains, line_uuid)) {
            return ResultConstant.FAILED;
        }
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
        if (ObjectUtils.isEmpty(line_uuid, text_length)) {
            return ResultConstant.FAILED;
        }
        return currentDao.updateTextLength("item_room", line_uuid, text_length);
    }

    @RequestMapping("roomIn")
    public Integer RoomIn(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        List<String> roomGuids = MapUtils.getStrList("roomGuids", jsonMap);
        if (ObjectUtils.isEmpty(roomGuids)) {
            throw BaseException.getDefault();
        }
        List<RoomIn> roomIns = new ArrayList<>();
        for (String roomGuid : roomGuids) {
            RoomIn roomIn = new RoomIn();
            BeanUtils.populate(roomIn, jsonMap);
            //roomIn.setDate(MapUtils.getLong("date", jsonMap));
            //roomIn.setMoney(MapUtils.getDouble("money", jsonMap));
            //roomIn.setSource(MapUtils.getString("source", jsonMap));
            //roomIn.setRemark(MapUtils.getString("remark", jsonMap));
            //roomIn.setTypeGuid(MapUtils.getString("type", jsonMap));
            roomIn.setGuid(IdUtil.simpleUUID());
            roomIn.setRoomGuid(roomGuid);
            roomIns.add(roomIn);
        }
        return roomService.roomIn(roomIns);
    }

    @RequestMapping("roomOut")
    public Integer roomOut(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        List<String> roomGuids = MapUtils.getStrList("roomGuids", jsonMap);
        if (ObjectUtils.isEmpty(roomGuids)) {
            throw BaseException.getDefault();
        }
        List<RoomOut> roomOuts = new ArrayList<>();
        for (String roomGuid : roomGuids) {
            RoomOut roomOut = new RoomOut();
            BeanUtils.populate(roomOut, jsonMap);
            //roomOut.setDate(MapUtils.getLong("date", jsonMap));
            //roomOut.setMoney(MapUtils.getDouble("money", jsonMap));
            //roomOut.setDestination(MapUtils.getString("destination", jsonMap));
            //roomOut.setRemark(MapUtils.getString("remark", jsonMap));
            //roomOut.setTypeGuid(MapUtils.getString("type", jsonMap));
            roomOut.setGuid(IdUtil.simpleUUID());
            roomOut.setRoomGuid(roomGuid);
            roomOuts.add(roomOut);
        }
        return roomService.roomOut(roomOuts);
    }

    @RequestMapping("test")
    public void test() {

        //currentDao.addField("item_room", "aa" + DateUtil.now(), 1, null);
    }
}
