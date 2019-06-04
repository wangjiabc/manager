package com.voucher.manage2.service.impl;

import com.voucher.manage2.service.RoomService;
import com.voucher.manage2.tkmapper.entity.RoomIn;
import com.voucher.manage2.tkmapper.mapper.RoomInMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //TODO 资产操作日志记录
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer roomIn(List<RoomIn> roomIns) {
        return roomInMapper.insertList(roomIns);
    }
}
