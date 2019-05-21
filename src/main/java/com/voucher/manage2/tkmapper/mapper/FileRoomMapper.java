package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.FileRoom;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface FileRoomMapper extends Mapper<FileRoom>, InsertListMapper<FileRoom> {
}