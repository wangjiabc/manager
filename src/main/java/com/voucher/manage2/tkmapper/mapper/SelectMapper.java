package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SelectMapper extends Mapper<Select>, InsertListMapper<Select> {
}