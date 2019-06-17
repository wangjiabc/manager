package com.voucher.manage.daoImpl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.daoModel.Table_alias;
import com.voucher.manage.daoRowMapper.RowMappersTableJoin;
import com.voucher.manage.daoRowMapper.RowMappersTableJoin2;
import com.voucher.manage.daoSQL.*;
import com.voucher.manage.daoSQL.annotations.DBTable;
import com.voucher.manage.tools.Md5;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.msg.ErrorMessageBean;
import com.voucher.manage2.msg.ExceptionMessage;
import com.voucher.manage2.utils.ObjectUtils;
import com.voucher.manage2.aop.interceptor.annotation.TimeConsume;
import com.voucher.manage2.constant.ResultConstant;
import com.voucher.manage2.constant.RoomConstant;
import com.voucher.manage2.tkmapper.entity.Select;
import com.voucher.manage2.tkmapper.entity.TableAlias;
import com.voucher.manage2.tkmapper.mapper.SelectMapper;
import com.voucher.manage2.tkmapper.mapper.TableAliasMapper;
import com.voucher.manage2.utils.MapUtils;
import com.voucher.manage2.utils.SpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentDaoImpl extends JdbcDaoSupport implements CurrentDao {

    @Autowired
    private TableAliasMapper tableAliasMapper;
    @Autowired
    private SelectMapper selectMapper;

    @Override
    public void createTable(String tableName, String joinParameter) {
        // TODO Auto-generated method stub

        String sql = "create table " + tableName + " (id int IDENTITY(1,1) NOT NULL," + joinParameter + " varchar(50) null) ";

        this.getJdbcTemplate().update(sql);

    }

    @Override
    public int alterTable(Boolean addOrDel, String tableName, String joinParameter, String lineName, String line_uuid) {
        // TODO Auto-generated method stub
        String sql;

        if (addOrDel) {
            String uuid = "item_" + Md5.GetMD5Code(UUID.randomUUID().toString());
            if (existTable(tableName) < 1) {
                createTable(tableName, joinParameter);
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
            if (ObjectUtils.isEmpty(list)) {
                return ResultConstant.FAILED;
            }
            Table_alias table_alias2 = list.get(0);

            sql = "alter table " + tableName + " drop column " + table_alias2.getLine_uuid();

            int i = DeleteExe.get(this.getJdbcTemplate(), table_alias);

            if (i < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

        }

        return this.getJdbcTemplate().update(sql) == 1 ? ResultConstant.SUCCESS : ResultConstant.FAILED;
    }

    @Override
    public Integer existTable(String tableName) {
        // TODO Auto-generated method stub
        String sql = "select COUNT(*) from dbo.sysobjects where id = object_id(N'[dbo].[" + tableName + "]')";

        return (int) this.getJdbcTemplate().queryForMap(sql).get("");
    }

    @Override
    @TimeConsume
    public Map selectTable(Object object, String joinParameter) throws ClassNotFoundException {
        // TODO Auto-generated method stub

        Map hMap = NewSelectSqlJoin.get(object, joinParameter);

        String sql = (String) hMap.get("sql");
        List params = (List) hMap.get("params");

        Map hMap2 = NewSelectSqlJoin.getCount(object, joinParameter);

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

            createTable(tableName2, joinParameter);
        }
        //表格数据
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
        //表头信息
        List<Table_alias> aliasList = SelectExe.get(this.getJdbcTemplate(), table_alias);

        List<Map<String, Object>> fixedTitleList = new ArrayList<>();
        List<Map<String, Object>> dynTitleList = new ArrayList<>();

        //下拉信息
        List<Select> selects = new ArrayList<>();
        try {
            selects = selectMapper.selectAll();
        } catch (Exception e) {
            // TODO: handle exception
            //e.printStackTrace();
        }
        Map<String, Map<Integer, String>> domains = new HashMap<>();
        for (Select select : selects) {
            Map<Integer, String> map = domains.get(select.getLineUuid());
            if (map == null) {
                domains.put(select.getLineUuid(), map = new HashMap<>());
            }
            map.put(select.getValue(), select.getName());
        }
        List<Map<String, Object>> dynLineInfoList = null;
        try {
            dynLineInfoList = tableAliasMapper.getDynLineInfo();
        } catch (Exception e) {
            // TODO: handle exception
            //e.printStackTrace();
        }
        Map<String, Object> dynLineInfoMap = null;
        if (ObjectUtils.isNotEmpty(dynLineInfoList)) {
            dynLineInfoMap = new HashMap<>();
            for (Map<String, Object> map : dynLineInfoList) {
                dynLineInfoMap.put(MapUtils.getString("line_uuid", map), map.get("max_length"));
            }
        }

        String REGEX = "item_";
        Pattern pattern = Pattern.compile(REGEX);

        for (Table_alias table_alias1 : aliasList) {
            Map<String, Object> dynTitleMap = new HashMap<>(8);
            String line_uuid = table_alias1.getLine_uuid();
            String table_name = table_alias1.getTable_name();
            dynTitleMap.put("field", line_uuid);
            dynTitleMap.put("title", table_alias1.getLine_alias());
            dynTitleMap.put("rowType", table_alias1.getRow_type());
            dynTitleMap.put("roomType", table_alias1.getRoom_type());
            Matcher matcher = pattern.matcher(table_name);
            if (!matcher.find()) {
                fixedTitleList.add(dynTitleMap);
            } else {
            	fixedTitleList.add(dynTitleMap);
				Map<Integer, String> domain = domains.get(line_uuid);
				try {
					Object text_length = dynLineInfoMap.get(line_uuid);
					if (ObjectUtils.isNotEmpty(text_length)) {
						dynTitleMap.put("text_length", text_length);
					}
					if (ObjectUtils.isNotEmpty(domains)) {
						dynTitleMap.put("domains", domain);
					}
					dynTitleList.add(dynTitleMap);
				} catch (Exception e) {
					// TODO: handle exception
				}
            }

        }
        MyTestUtil.print(aliasList);
        MyTestUtil.print(list);

        Map map = new HashMap<>(8);
        map.put("rows", list);
        map.put("total", total);
        map.put("fixedTitle", fixedTitleList);
        map.put("dynTitle", dynTitleList);

        return map;
    }

    @Override
    public Map selectTable(Object[] objects, String[][] joinParameters, String[] itemjoinParameters) throws ClassNotFoundException {
        // TODO Auto-generated method stub

        Map hMap = NewSelectSqlJoin2.get(objects, joinParameters, itemjoinParameters);

        String sql = (String) hMap.get("sql");
        List params = (List) hMap.get("params");

        Map hMap2 = NewSelectSqlJoin2.getCount(objects, joinParameters);

        String sql2 = (String) hMap2.get("sql");
        List params2 = (List) hMap2.get("params");

        List<Map<String, Object>> fixedTitleList = new ArrayList<>();
        List<Map<String, Object>> dynTitleList = new ArrayList<>();

        List<Class> classNames = new ArrayList<>();

        List<String> tableNames = new ArrayList<>();

        int s = 0;
        for (Object object : objects) {

            Class className = object.getClass();
            String name = className.getName(); // 从控制台输入一个类名，我们输入User即可
            Class<?> cl = Class.forName(name); // 加载类，如果该类不在默认路径底下，会报
            // java.lang.ClassNotFoundException
            DBTable dbTable = cl.getAnnotation(DBTable.class);

            String tableName = (dbTable.name().length() < 1) ? cl.getName() : dbTable.name();// 获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

            classNames.add(className);
            tableNames.add(tableName);

            int exist = existTable("item_" + tableName.substring(1, tableName.length() - 1));

            if (exist < 1) {

                String tableName2 = "[item_" + tableName.substring(1);

                try {
                    String joinParameter = itemjoinParameters[s];
                    createTable(tableName2, joinParameter);
                    s++;

                } catch (Exception e) {
                    // TODO: handle exception
                    tableName = name;
                }

            }

            Table_alias table_alias = new Table_alias();
            table_alias.setLimit(1000);
            table_alias.setOffset(0);
            table_alias.setNotIn("id");
            String[] where = {"del=", "false"};
            table_alias.setWhere(where);

            tableName = tableName.substring(1, tableName.length() - 1);

            // String[] where = {"table_name=", tableName};

            System.out.println(tableName);

            // table_alias.setWhere(where);
            // 表头信息
            List<Table_alias> aliasList = SelectExe.get(this.getJdbcTemplate(), table_alias);

            // 下拉信息
            List<Select> selects = new ArrayList<>();
            try {
                selects = selectMapper.selectAll();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            Map<String, Map<Integer, String>> domains = new HashMap<>();
            for (Select select : selects) {
                Map<Integer, String> map = domains.get(select.getLineUuid());
                if (map == null)
                    domains.put(select.getLineUuid(), map = new HashMap<>());
                map.put(select.getValue(), select.getName());
            }
            List<Map<String, Object>> dynLineInfoList = null;
            try {
                dynLineInfoList = tableAliasMapper.getDynLineInfo();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            Map<String, Object> dynLineInfoMap = null;
            if (ObjectUtils.isNotEmpty(dynLineInfoList)) {
                dynLineInfoMap = new HashMap<>();
                for (Map<String, Object> map : dynLineInfoList) {
                    dynLineInfoMap.put(MapUtils.getString("line_uuid", map), map.get("max_length"));
                }
            }

            String REGEX = "item_";
            Pattern pattern = Pattern.compile(REGEX);

            for (Table_alias table_alias1 : aliasList) {
                Map<String, Object> dynTitleMap = new HashMap<>();
                String line_uuid = table_alias1.getLine_uuid();
                String table_name = table_alias1.getTable_name();
                dynTitleMap.put("field", line_uuid);
                dynTitleMap.put("title", table_alias1.getLine_alias());
                dynTitleMap.put("type", table_alias1.getRow_type());
                Matcher matcher = pattern.matcher(table_name);
                if (!matcher.find()) {
                    fixedTitleList.add(dynTitleMap);
                } else {
                    Map<Integer, String> domain = domains.get(line_uuid);
                    Object text_length = dynLineInfoMap.get(line_uuid);
                    if (ObjectUtils.isNotEmpty(text_length))
                        dynTitleMap.put("text_length", text_length);
                    if (ObjectUtils.isNotEmpty(domains))
                        dynTitleMap.put("domains", domain);
                    dynTitleList.add(dynTitleMap);
                }

            }
            MyTestUtil.print(aliasList);

        }

        Class[] classNames_ = new Class[classNames.size()];
        String[] tableName_ = new String[tableNames.size()];

        // 表格数据
        List<Map> list = this.getJdbcTemplate().query(sql, params.toArray(),
                new RowMappersTableJoin2(getJdbcTemplate(), classNames.toArray(classNames_), tableNames.toArray(tableName_)));

        int total = (int) this.getJdbcTemplate().queryForMap(sql2, params2.toArray()).get("");

        Map map = new HashMap<>();
        map.put("rows", list);
        map.put("total", total);
        map.put("fixedTitle", fixedTitleList);
        map.put("dynTitle", dynTitleList);

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
            return ResultConstant.FAILED;
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
    @Transactional(rollbackFor = Exception.class)
    public Integer updateTable(String tableName, String line_uuid, String value) {
        Table_alias table_alias = new Table_alias();
        table_alias.setLine_alias(value);

        String[] where = {"line_uuid=", line_uuid};

        table_alias.setWhere(where);

        return UpdateExe.get(this.getJdbcTemplate(), table_alias);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateItmeTable(Map<String, Object> roomMap) throws InvocationTargetException, IllegalAccessException {
        Room room = new Room();
        BeanUtils.populate(room, roomMap);
        String[] where = {"guid=", room.getGuid() + ""};
        room.setId(null);
        room.setGuid(null);
        room.setAsset_check_date(null);
        room.setHidden_check_date(null);
        room.setWhere(where);
        MyTestUtil.print(room);
        Integer upNum = UpdateExe.get(this.getJdbcTemplate(), room);
        if (upNum != 1) {
            throw BaseException.getDefault();
        }
        StringBuffer sqlBuf = new StringBuffer(100);
        sqlBuf.append("update item_room set ");
        boolean isnotempty = false;
        roomMap.forEach((k, v) -> {
            if (k.startsWith("item") && ObjectUtils.isNotEmpty(k, v)) {
                System.out.println(k + "='" + v + "',");
                sqlBuf.append(k + "='" + v + "',");
            }
        });
        try {
            sqlBuf.deleteCharAt(sqlBuf.lastIndexOf(","));
            sqlBuf.append("where guid='" + MapUtils.getString("guid", roomMap) + "'");
            isnotempty = true;
        } catch (Exception e) {
            //e.printStackTrace();
            throw BaseException.getDefault(e);
        }

        //String sql = " update " + tableName + " set " + line_uuid + "=" + value + " where guid=" + guid;

        //int i = this.getJdbcTemplate().update(sql);
        //return i;
        //return this.getJdbcTemplate().update(sqlBuf.toString()) == 1 ? ResultConstant.SUCCESS : ResultConstant.FAILED;
        if (isnotempty) {
            return this.getJdbcTemplate().update(sqlBuf.toString()) == 1 ? ResultConstant.SUCCESS : ResultConstant.FAILED;
        } else {
            return ResultConstant.SUCCESS;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delItmeTable(String guid) {
        Room room = new Room();
        room.setDel(false);
        String[] where = {"guid=", guid};
        room.setWhere(where);
        Integer upNum = UpdateExe.get(this.getJdbcTemplate(), room);
        if (upNum != 1) {
            return ResultConstant.FAILED;
        }
        String sql = "update item_room set del = 'true' where guid = '" + guid + "'";

        return this.getJdbcTemplate().update(sql) == 1 ? ResultConstant.SUCCESS : ResultConstant.FAILED;
    }

    @Override
    public Integer recycleRoom(List<String> guidList) {
        StringBuffer strBuf = new StringBuffer("update room set del = 'true' where guid in (");
        guidList.forEach(e -> strBuf.append("'" + e + "',"));
        strBuf.replace(strBuf.length() - 1, strBuf.length(), ")");
        return this.getJdbcTemplate().update(strBuf.toString()) == guidList.size() ? ResultConstant.SUCCESS : ResultConstant.FAILED;
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
            return ResultConstant.FAILED;
        }
        return result == 1 ? ResultConstant.SUCCESS : ResultConstant.FAILED;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        return this.getJdbcTemplate().update(sql) == 1 ? ResultConstant.SUCCESS : ResultConstant.FAILED;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addField(String tableName, String fieldName, Integer filedType, Map<Integer, String> selectValue, Integer roomType) {
        //1文本,2数字,3时间,4下拉
        String sqlType = RoomConstant.ROW_TYPE_MAP.get(filedType);
        if (ObjectUtils.isEmpty(filedType)) {
            return ResultConstant.FAILED;
        }
        String line_uuid = "item_" + Md5.GetMD5Code(UUID.randomUUID().toString()) + "";
        TableAlias tableAlias = new TableAlias();
        tableAlias.setTableName(tableName);
        tableAlias.setLineAlias(fieldName);
        tableAlias.setRowType(filedType);
        tableAlias.setRoomType(roomType);
        tableAlias.setLineUuid(line_uuid);
        tableAlias.setDate(System.currentTimeMillis());
        //int update = InsertExe.get(this.getJdbcTemplate(), tableAlias);
        int update = tableAliasMapper.insertSelective(tableAlias);

        //回滚事务
        SpringUtils.setRollbackOnly(update < 1);

        String sql = "alter table " + tableName + " add " + line_uuid + sqlType;
        if (selectValue != null) {
            List<Select> selects = new ArrayList<>();
            for (Map.Entry<Integer, String> entry : selectValue.entrySet()) {
                Select select = new Select();
                select.setLineUuid(line_uuid);
                select.setName(entry.getValue());
                select.setValue(entry.getKey());
                selects.add(select);
            }

            update = selectMapper.insertList(selects);
            SpringUtils.setRollbackOnly(update < 1);
        }
        //int i = 1 / 0;
        //update = this.getJdbcTemplate().update(sql);
        //System.out.println(update);
        return this.getJdbcTemplate().update(sql) == 0 ? ResultConstant.SUCCESS : ResultConstant.FAILED;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateSelect(List<Select> selects) {
        if (selects == null) {
            return ResultConstant.FAILED;
        }
        int update = 0;
        for (Select select : selects) {
            Example example = new Example(Select.class);
            example.createCriteria().andEqualTo("lineUuid", select.getLineUuid())
                    .andEqualTo("value", select.getValue());
            selectMapper.updateByExampleSelective(select, example);
            update++;
        }
        SpringUtils.setRollbackOnly(update != selects.size());
        return update == selects.size() ? ResultConstant.SUCCESS : ResultConstant.FAILED;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateTextLength(String item_room, String line_uuid, Integer text_length) {
        //字段校验
        Example example = new Example(TableAlias.class);
        example.createCriteria().andEqualTo("table_name", item_room)
                .andEqualTo("line_uuid", line_uuid);
        List<TableAlias> tableAliases = tableAliasMapper.selectByExample(example);
        if (ObjectUtils.isEmpty(tableAliases) && tableAliases.size() != 1) {
            return ResultConstant.FAILED;
        }
        return tableAliasMapper.updateTextLength(item_room, line_uuid, text_length);
    }

    //@PostConstruct
    //public void test() {
    //    addField("item_room", "aa" + DateUtil.now(), (byte) 1, null);
    //}


}
