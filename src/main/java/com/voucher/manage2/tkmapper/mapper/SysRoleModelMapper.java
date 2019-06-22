package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.SysModel;
import com.voucher.manage2.tkmapper.entity.SysRoleModel;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleModelMapper extends Mapper<SysRoleModel>, InsertListMapper<SysRoleModel> {

}