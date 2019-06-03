package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.RoomIn;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface RoomInMapper extends Mapper<RoomIn>, InsertListMapper<RoomIn> {
}