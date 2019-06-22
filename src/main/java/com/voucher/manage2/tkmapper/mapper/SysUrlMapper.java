package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.SysUrl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUrlMapper extends Mapper<SysUrl>, InsertListMapper<SysUrl> {
    @Select("SELECT su.url,su.name FROM sys_model_url smu LEFT JOIN sys_url su ON smu.url=su.url WHERE  smu.model_guid = '${modelGuid}'")
    List<SysUrl> getUrlsByModel(@Param("modelGuid") String modelGuid);
}