package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface SelectMapper extends Mapper<Select>, InsertListMapper<Select> {
}