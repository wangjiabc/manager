package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.RoomOut;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface RoomOutMapper extends Mapper<RoomOut>, InsertListMapper<RoomOut> {
}