package com.voucher.manage2.controller;

import com.voucher.manage2.dto.RoomLogDTO;
import com.voucher.manage2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("getLogByRoomGuid")
    public Object getLogByRoomGuid(String roomGuid) {
        List<RoomLogDTO> logs = roomService.getLogByRoomGuid(roomGuid);
        return logs;
    }
}
