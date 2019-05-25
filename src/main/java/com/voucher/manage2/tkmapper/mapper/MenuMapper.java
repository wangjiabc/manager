package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.tkmapper.entity.Menu;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface MenuMapper extends Mapper<MenuDTO>, InsertListMapper<MenuDTO> {
}