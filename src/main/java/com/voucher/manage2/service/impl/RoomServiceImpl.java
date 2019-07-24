package com.voucher.manage2.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.constant.RoomLogConstant;
import com.voucher.manage2.dto.RoomLogDTO;
import com.voucher.manage2.service.RoomService;
import com.voucher.manage2.tkmapper.entity.Room;
import com.voucher.manage2.tkmapper.entity.RoomIn;
import com.voucher.manage2.tkmapper.entity.RoomLog;
import com.voucher.manage2.tkmapper.entity.RoomOut;
import com.voucher.manage2.tkmapper.mapper.RoomInMapper;
import com.voucher.manage2.tkmapper.mapper.RoomLogMapper;
import com.voucher.manage2.tkmapper.mapper.RoomMapper;
import com.voucher.manage2.tkmapper.mapper.RoomOutMapper;
import com.voucher.manage2.utils.CommonUtils;
import com.voucher.manage2.utils.RoomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

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
    private RoomMapper roomMapper;
    @Autowired
    private RoomLogMapper roomLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer roomIn(List<RoomIn> roomIns) {
        List<RoomLogDTO> roomLogs = new ArrayList<>();
        List<String> roomGuids = new ArrayList<>();
        for (RoomIn roomIn : roomIns) {
            RoomLogDTO roomLog = new RoomLogDTO();
            roomLog.setDate(System.currentTimeMillis());
            roomLog.setGuid(IdUtil.simpleUUID());
            roomLog.setOperationGuid(roomIn.getGuid());
            roomLog.setIntroduction(RoomUtils.getRoomLogIntroduction(roomIn));
            roomLog.setOperationType(roomIn.getTypeGuid());
            roomLog.setRoomGuid(roomIn.getRoomGuid());
            roomLog.setUserGuid(CommonUtils.getCurrentUserGuid());
            roomLog.setLogType(RoomLogConstant.IN.type);
            roomLogs.add(roomLog);
            roomGuids.add(roomIn.getRoomGuid());
        }
        //更改资产状态
        Room roomTemp = new Room();
        roomTemp.setInDate(roomIns.get(0).getDate());
        roomTemp.setInType(roomIns.get(0).getTypeGuid());
        Example example = new Example(Room.class);
        example.createCriteria().andIn("guid", roomGuids);
        roomMapper.updateByExampleSelective(roomTemp, example);

        roomInMapper.insertList(roomIns);
        return roomLogMapper.insertList(roomLogs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer roomOut(List<RoomOut> roomOuts) {
        List<RoomLogDTO> roomLogs = new ArrayList<>();
        List<String> roomGuids = new ArrayList<>();
        for (RoomOut roomOut : roomOuts) {
            RoomLogDTO roomLog = new RoomLogDTO();
            roomLog.setGuid(IdUtil.simpleUUID());
            roomLog.setDate(System.currentTimeMillis());
            roomLog.setOperationGuid(roomOut.getGuid());
            roomLog.setIntroduction(RoomUtils.getRoomLogIntroduction(roomOut));
            roomLog.setOperationType(roomOut.getTypeGuid());
            roomLog.setRoomGuid(roomOut.getRoomGuid());
            roomLog.setLogType(RoomLogConstant.OUT.type);
            roomLog.setUserGuid(CommonUtils.getCurrentUserGuid());
            roomLogs.add(roomLog);
            roomGuids.add(roomOut.getRoomGuid());
        }

        //更改资产状态
        Room roomTemp = new Room();
        roomTemp.setState(RoomUtils.getRoomStateByTypeGuid(roomOuts.get(0).getTypeGuid()));
        Example example = new Example(Room.class);
        example.createCriteria().andIn("guid", roomGuids);
        roomMapper.updateByExampleSelective(roomTemp, example);

        roomOutMapper.insertList(roomOuts);
        return roomLogMapper.insertList(roomLogs);
    }

    @Override
    public List<RoomLogDTO> getLogByRoomGuid(String roomGuid) {
        Weekend<RoomLogDTO> roomLogWeekend = new Weekend<>(RoomLogDTO.class);
        roomLogWeekend.weekendCriteria().andEqualTo(RoomLog::getRoomGuid, roomGuid);
        List<RoomLogDTO> roomLogDTOS = roomLogMapper.selectByExample(roomLogWeekend);
        return roomLogDTOS;
    }

}
