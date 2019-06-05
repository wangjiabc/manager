package com.voucher.manage2.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage2.service.RoomService;
import com.voucher.manage2.tkmapper.entity.RoomIn;
import com.voucher.manage2.tkmapper.entity.RoomLog;
import com.voucher.manage2.tkmapper.entity.RoomOut;
import com.voucher.manage2.tkmapper.mapper.RoomInMapper;
import com.voucher.manage2.tkmapper.mapper.RoomLogMapper;
import com.voucher.manage2.tkmapper.mapper.RoomOutMapper;
import com.voucher.manage2.utils.RoomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/6/3
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomInMapper roomInMapper;
    @Autowired
    private RoomOutMapper roomOutMapper;
    @Autowired
    private RoomLogMapper roomLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer roomIn(List<RoomIn> roomIns) {
        List<RoomLog> roomLogs = new ArrayList<>();
        for (RoomIn roomIn : roomIns) {
            RoomLog roomLog = new RoomLog();
            roomLog.setDate(System.currentTimeMillis());
            roomLog.setGuid(IdUtil.simpleUUID());
            roomLog.setOperationGuid(roomIn.getGuid());
            roomLog.setIntroduction(RoomUtils.getRoomLogIntroduction(roomIn));
            roomLog.setOperationType(roomIn.getTypeGuid());
            roomLogs.add(roomLog);
        }
        roomInMapper.insertList(roomIns);
        return roomLogMapper.insertList(roomLogs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer roomOut(List<RoomOut> roomOuts) {
        List<RoomLog> roomLogs = new ArrayList<>();
        List<String> roomGuids = new ArrayList<>();
        for (RoomOut roomOut : roomOuts) {
            RoomLog roomLog = new RoomLog();
            roomLog.setGuid(IdUtil.simpleUUID());
            roomLog.setDate(System.currentTimeMillis());
            roomLog.setOperationGuid(roomOut.getGuid());
            roomLog.setIntroduction(RoomUtils.getRoomLogIntroduction(roomOut));
            roomLog.setOperationType(roomOut.getTypeGuid());
            roomLogs.add(roomLog);
            roomGuids.add(roomOut.getRoomGuid());
        }

        roomOutMapper.insertList(roomOuts);
        return roomLogMapper.insertList(roomLogs);
    }

}
