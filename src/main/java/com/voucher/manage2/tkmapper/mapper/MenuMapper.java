package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<MenuDTO>, InsertListMapper<MenuDTO> {

}