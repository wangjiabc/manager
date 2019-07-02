package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.dto.SysRouterDTO;
import com.voucher.manage2.tkmapper.entity.SysRouter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysRouterMapper extends Mapper<SysRouterDTO>, InsertListMapper<SysRouterDTO> {
    //根据用户去获取所有路由
    @Select("SELECT * FROM sys_router WHERE sys_router.guid IN (SELECT srr.router_guid FROM sys_user_role sur LEFT JOIN  sys_role sr ON sur.role_guid = sr.guid LEFT " +
            "JOIN sys_role_router srr ON sr.guid=srr.role_guid WHERE sur.user_guid = '${userGuid}')" +
            "AND sys_router.root_guid = '${rootGuid}'")
    List<SysRouterDTO> selectRouter(@Param("userGuid") String userGuid, @Param("rootGuid") String rootGuid);
}