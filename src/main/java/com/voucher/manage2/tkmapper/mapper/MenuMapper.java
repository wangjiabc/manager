package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<MenuDTO>, InsertListMapper<MenuDTO> {
    @Select("SELECT * FROM menu WHERE menu.guid IN (SELECT srm.menu_guid FROM sys_user_role sur LEFT JOIN  sys_role sr ON sur.role_guid = sr.guid LEFT " +
            "JOIN sys_role_menu srm ON sr.guid=srm.role_guid WHERE sur.user_guid = '${currentUserGuid}')" +
            "AND menu.root_guid = '${rootGuid}'")
    List<MenuDTO> selectFileMenu(@Param("currentUserGuid") String currentUserGuid, @Param("rootGuid") String rootGuid);
}