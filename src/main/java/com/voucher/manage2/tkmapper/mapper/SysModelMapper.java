package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.SysModel;
import com.voucher.manage2.tkmapper.entity.SysUrl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysModelMapper extends Mapper<SysModel>, InsertListMapper<SysModel> {
    @Select("SELECT sm.guid,sm.name FROM sys_role_model srm LEFT JOIN sys_model sm ON srm.model_guid=sm.guid WHERE  srm.role_guid = '${roleGuid}'")
    List<SysModel> getModelsByRoleGuid(@Param("roleGuid") String roleGuid);

}