package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.dto.RoomLogDTO;
import com.voucher.manage2.tkmapper.entity.RoomLog;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface RoomLogMapper extends Mapper<RoomLogDTO>, InsertListMapper<RoomLogDTO> {
}