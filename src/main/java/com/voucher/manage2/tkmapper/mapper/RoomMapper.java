package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.Room;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface RoomMapper extends Mapper<Room>, InsertListMapper<Room> {
}