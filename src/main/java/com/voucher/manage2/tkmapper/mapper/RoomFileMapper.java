package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.RoomFile;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface RoomFileMapper extends Mapper<RoomFile>, InsertListMapper<RoomFile> {
}