package com.voucher.manage2.controller;

import com.alibaba.fastjson.JSONObject;
import com.voucher.manage2.constant.ResultConstant;
import com.voucher.manage2.constant.RoomLogConstant;
import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.service.RoomService;
import com.voucher.manage2.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @author lz
 * @description
 * @date 2019/7/11
 */
@RestController
@RequestMapping("/roomLog")
public class RoomLogController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("getLogByRoomGuid")
    public Object getLogByRoomGuid(String roomGuid) {
        return roomService.getLogByRoomGuid(roomGuid);
    }

    @RequestMapping("getLogFileMenu")
    public Object getLogFileMenu(@RequestBody JSONObject jsonMap) {
        String roomGuid = jsonMap.getString("roomGuid");
        Integer logType = jsonMap.getInteger("logType");
        String operationType = jsonMap.getString("operationType");
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setGuid(RoomLogConstant.TYPE_MENU_GUID.get(logType));
        String[] roomGuids = {roomGuid};
        menuService.selectFileMenu(menuDTO, roomGuids);
        if (ObjectUtils.isEmpty(menuDTO.getChildList())) {
            return ResultConstant.FAILED;
        }
        return menuDTO.getChildList().stream().filter(e -> operationType.equals(e.getGuid())).collect(Collectors.toList()).get(0);
    }
}
