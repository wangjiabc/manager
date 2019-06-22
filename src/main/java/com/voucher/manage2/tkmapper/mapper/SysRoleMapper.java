package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole>, InsertListMapper<SysRole> {

    @Select("SELECT sr.guid,sr.name FROM sys_user_role sur LEFT JOIN sys_role sr ON sur.role_guid = sr.guid WHERE sur.user_guid = '${userGuid}'")
    List<SysRole> getRolesByUserGuid(@Param("userGuid") String userGuid);
}