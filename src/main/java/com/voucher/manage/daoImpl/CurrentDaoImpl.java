package com.voucher.manage.daoImpl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.daoModel.Table_alias;
import com.voucher.manage.daoRowMapper.RowMappersTableJoin;
import com.voucher.manage.daoSQL.*;
import com.voucher.manage.daoSQL.annotations.DBTable;
import com.voucher.manage.tools.Md5;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.manage2.aop.annotation.TimeConsume;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.common.SqlServerMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CurrentDaoImpl extends JdbcDaoSupport implements CurrentDao {

    @Override
    public void createTable(String tableName) {
        // TODO Auto-generated method stub

        String sql = "create table " + tableName + " (id int IDENTITY(1,1) NOT NULL,guid varchar(255) null) ";

        this.getJdbcTemplate().update(sql);

    }

    @Override
    public int alterTable(Boolean addOrDel, String tableName, String lineName, String line_uuid) {
        // TODO Auto-generated method stub
        String sql;

        if (addOrDel) {
            String uuid = "item_" + Md5.GetMD5Code(UUID.randomUUID().toString());
            if (existTable(tableName) < 1) {
                createTable(tableName);
            }

            sql = "alter table " + tableName + " add " + uuid + " varchar(max) null ";

            Table_alias table_alias = new Table_alias();

            table_alias.setTable_name(tableName);
            table_alias.setLine_alias(lineName);
            table_alias.setLine_uuid(uuid);
            table_alias.setDate(System.currentTimeMillis());

            int i = InsertExe.get(this.getJdbcTemplate(), table_alias);

            if (i < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

        } else {

            Table_alias table_alias = new Table_alias();

            table_alias.setLimit(1);
            table_alias.setOffset(0);
            table_alias.setNotIn("id");

            String[] where = {"table_name=", tableName, "line_uuid=", line_uuid};

            table_alias.setWhere(where);

            List<Table_alias> list = SelectExe.get(this.getJdbcTemplate(), table_alias);
            if (list.isEmpty())
                return -1;
            Table_alias table_alias2 = list.get(0);

            sql = "alter table " + tableName + " drop column " + table_alias2.getLine_uuid();

            int i = DeleteExe.get(this.getJdbcTemplate(), table_alias);

            if (i < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

        }

        return this.getJdbcTemplate().update(sql) == -1 ? 0 : 1;
    }

    @Override
    public Integer existTable(String tableName) {
        // TODO Auto-generated method stub
        String sql = "select COUNT(*) from dbo.sysobjects where id = object_id(N'[dbo].[" + tableName + "]')";

        int count = (int) this.getJdbcTemplate().queryForMap(sql).get("");

        return count;
    }

    @Override
    @TimeConsume
    public Map selectTable(Object object) throws ClassNotFoundException {
        // TODO Auto-generated method stub

        Map hMap = NewSelectSqlJoin.get(object);

        String sql = (String) hMap.get("sql");
        List params = (List) hMap.get("params");

        Map hMap2 = NewSelectSqlJoin.getCount(object);

        String sql2 = (String) hMap2.get("sql");
        List params2 = (List) hMap2.get("params");

        Class className = object.getClass();
        String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
        Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
        DBTable dbTable = cl.getAnnotation(DBTable.class);

        String tableName = (dbTable.name().length() < 1) ? cl.getName() : dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

        int exist = existTable("item_" + tableName.substring(1, tableName.length() - 1));

        if (exist < 1) {

            String tableName2 = "[item_" + tableName.substring(1);

            createTable(tableName2);

        }

        List<Map> list = this.getJdbcTemplate().query(sql, params.toArray(), new RowMappersTableJoin(getJdbcTemplate(), className, tableName));

        int total = (int) this.getJdbcTemplate().queryForMap(sql2, params2.toArray()).get("");

        Table_alias table_alias = new Table_alias();
        table_alias.setLimit(1000);
        table_alias.setOffset(0);
        table_alias.setNotIn("id");
        String[] where = {"del=", "false"};
        table_alias.setWhere(where);

        tableName = tableName.substring(1, tableName.length() - 1);

        //String[] where = {"table_name=", tableName};

        System.out.println(tableName);

        //table_alias.setWhere(where);

        List<Table_alias> aliasList = SelectExe.get(this.getJdbcTemplate(), table_alias);

        List<Map<String, Object>> fixedTitle = new ArrayList<>();
        List<Map<String, Object>> dynTitle = new ArrayList<>();
        for (Table_alias table_alias1 : aliasList) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("field", table_alias1.getLine_uuid());
            hashMap.put("title", table_alias1.getLine_alias());
            String table_name = table_alias1.getTable_name();
            if ("room".equals(table_name)) {
                fixedTitle.add(hashMap);
            } else if ("item_room".equals(table_name)) {
                dynTitle.add(hashMap);
            }
        }
        MyTestUtil.print(aliasList);
        MyTestUtil.print(list);

        Map map = new HashMap<>();
        //list.stream().forEach(e -> {
        //    e.put("room_property", RoomConstant.propertyMap.get(e.get("room_property")));
        //    e.put("state", RoomConstant.stateMap.get(e.get("state")));
        //    e.put("neaten_flow", RoomConstant.neatenMap.get(e.get("neaten_flow")));
        //    e.put("hidden", RoomConstant.hiddenMap.get(e.get("hidden")));
        //});
        map.put("rows", list);
        map.put("total", total);
        map.put("fixedTitle", fixedTitle);
        map.put("dynTitle", dynTitle);

        return map;
    }

    @Override
    public Integer insertTable(Object object, String val) throws ClassNotFoundException {
        // TODO Auto-generated method stub

        String fields = "";

        String values = "";

        List params = new ArrayList<>();

        Class className = object.getClass();
        String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
        Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
        DBTable dbTable = cl.getAnnotation(DBTable.class);

        String tableName = (dbTable.name().length() < 1) ? cl.getName() : dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

        String tableName2 = "[item_" + tableName.substring(1, tableName.length());

        val = val.substring(1, val.length() - 1);

        String[] valStrings = val.split(",");

        int i = 0;

        if (val != null && !val.equals("")) {
            for (String valString : valStrings) {

                valString = valString.substring(1, valString.length() - 1);

                if (i % 2 == 0) {
                    fields = fields + "," + valString;
                } else if (i != 0 && i % 2 != 0) {
                    values = values + ",?";
                    params.add(valString);
                }

                i++;
            }
        } else {
            return 0;
        }

        fields = fields.substring(1, fields.length());
        values = values.substring(1, values.length());

        int result = 0;

        String sql = " insert into " + tableName2 + " (" + fields + ") " +
                " values (" + values + ")";

        result = this.getJdbcTemplate().update(sql, params.toArray());

        result = InsertExe.get(this.getJdbcTemplate(), object);

        return result;

    }

    @Override
    @Transactional
    public Integer updateTable(String tableName, String line_uuid, String value) {
        Table_alias table_alias = new Table_alias();
        table_alias.setLine_alias(value);

        String[] where = {"line_uuid=", line_uuid};

        table_alias.setWhere(where);

        return UpdateExe.get(this.getJdbcTemplate(), table_alias);
    }

    @Override
    @Transactional
    public Integer updateItmeTable(Map<String, Object> roomMap) throws InvocationTargetException, IllegalAccessException {
        Room room = new Room();
        BeanUtils.populate(room, roomMap);
        String[] where = {"id=", room.getId() + ""};
        room.setWhere(where);
        room.setId(null);
        Integer upNum = UpdateExe.get(this.getJdbcTemplate(), room);
        if (upNum != 1) {
            return 0;
        }
        StringBuffer sqlBuf = new StringBuffer(100);
        sqlBuf.append("update item_room set ");
        roomMap.forEach((k, v) -> {
            if (k.startsWith("item")) {
                sqlBuf.append(k + "='" + v + "',");
            }
        });
        sqlBuf.deleteCharAt(sqlBuf.lastIndexOf(","));
        sqlBuf.append(" where guid = '" + room.getGuid() + "'");

        //String sql = " update " + tableName + " set " + line_uuid + "=" + value + " where guid=" + guid;

        //int i = this.getJdbcTemplate().update(sql);
        //return i;
        return this.getJdbcTemplate().update(sqlBuf.toString()) == -1 ? 0 : 1;
    }

    @Override
    @Transactional
    public Integer delItmeTable(String guid) {
        Room room = new Room();
        room.setDel(false);
        String[] where = {"guid=", guid};
        room.setWhere(where);
        Integer upNum = UpdateExe.get(this.getJdbcTemplate(), room);
        if (upNum != 1) {
            return 0;
        }
        String sql = "update item_room set del = 'true' where guid = '" + guid + "'";

        return this.getJdbcTemplate().update(sql) == -1 ? 0 : 1;
    }

    @Override
    public Integer recycleRoom(List<String> guidList) {
        StringBuffer strBuf = new StringBuffer("update room set del = 'true' where guid in (");
        guidList.forEach(e -> strBuf.append("'" + e + "',"));
        strBuf.replace(strBuf.length() - 1, strBuf.length(), ")");
        return this.getJdbcTemplate().update(strBuf.toString()) == guidList.size() ? 1 : 0;
    }

    @Override
    public Integer insertResource(Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {

        Integer result;
        Room room = new Room();
        room.setGuid(IdUtil.simpleUUID());
        BeanUtils.populate(room, jsonMap);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("guid");
        jsonArray.add(room.getGuid());
        jsonMap.forEach((k, v) -> {
            if (!k.startsWith("temp_") && k.startsWith("item")) {
                jsonArray.add(k);
                jsonArray.add(v.toString());
            }
        });

        try {
            result = insertTable(room, jsonArray.toJSONString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
        return result == -1 ? 0 : 1;
    }

    @Override
    @Transactional
    public Integer recycleField(String line_uuid) {
        //Room room = new Room();
        //room.setDel(false);
        //String[] where = {"id=", guid};
        //room.setWhere(where);
        //Integer upNum = UpdateExe.get(this.getJdbcTemplate(), room);
        //Table_alias table_alias = new Table_alias();
        //table_alias.setDel(true);
        //table_alias.setLine_uuid(line_uuid);
        //String[] where = {"line_uuid=", line_uuid};
        //table_alias.setWhere(where);
        //return UpdateExe.get(this.getJdbcTemplate(), table_alias) == -1 ? 0 : 1;
        String sql = "update table_alias set del = 'true' where line_uuid = '" + line_uuid + "'";
        return this.getJdbcTemplate().update(sql) == -1 ? 0 : 1;
    }

    @Override
    public Integer addField(String fieldName, int type, String[] selectValue) {
        //1文本,2数字,3时间,4下拉

        return null;
    }
}
