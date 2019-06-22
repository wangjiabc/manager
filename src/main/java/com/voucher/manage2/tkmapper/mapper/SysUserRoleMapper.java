package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.SysUserRole;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserRoleMapper extends Mapper<SysUserRole>, InsertListMapper<SysUserRole> {
}