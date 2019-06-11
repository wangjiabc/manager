package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.TableAlias;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TableAliasMapper extends Mapper<TableAlias>, InsertListMapper<TableAlias> {
    /**
     * @Author lz
     * @Description:仅查询item_room 中varchar的长度
     * @return: {java.util.List<java.util.Map<java.lang.String,java.lang.Object>>}
     * @Date: 2019/5/14 11:22
     **/
    @Select("SELECT b.name line_uuid,b.max_length FROM sys.tables a join sys.columns b on b.object_id = a.object_id " +
            "join INFORMATION_SCHEMA.COLUMNS c on b.name=c.COLUMN_NAME and a.name=c.TABLE_NAME " +
            "where a.name='item_room' AND DATA_TYPE = 'varchar'")
    List<Map<String, Object>> getDynLineInfo();

    @Select("alter table ${tableName} alter column ${lineUuid} varchar(${textLength})")
    Integer updateTextLength(@Param("tableName") String tableName, @Param("lineUuid") String lineUuid, @Param("textLength") Integer textLength);
}