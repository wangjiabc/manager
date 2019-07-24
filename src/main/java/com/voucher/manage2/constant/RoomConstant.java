package com.voucher.manage2.constant;

import java.util.HashMap;
import java.util.Map;

public class RoomConstant {
    //空置
    public static final Integer VACANCY = 1;
    /**
     * @Description: 已出租
     **/
    public static final Integer RENTED = 2;
    //可出租
    public static final Integer RENT_AVAILABLE = 3;
    //不可出租
    public static final Integer RENT_UN_AVAILABLE = 4;
    //已划拨
    public static final Integer TRANSFERED = 5;
    //自用
    public static final Integer PRIVATE_USE = 6;
    //已出售
    public static final Integer SELLED = 7;
    //已拆迁
    public static final Integer REMOVEED = 8;
    //已拆除
    public static final Integer TEAR_DOWN = 9;
    //已灭失
    public static final Integer LOST = 10;
    //1:营业房,2:住宅房,3土地
    public static final Map<String, String> PROPERTY_MAP = new HashMap<String, String>() {
        {
            put("1", "营业房");
            put("2", "住宅房");
            put("3", "土地");
        }
    };

    public static final Map<Integer, String> STATE_MAP = new HashMap<Integer, String>() {
        {
            put(VACANCY, "空置");
            put(RENTED, "已出租");
            put(RENT_AVAILABLE, "可出租");
            put(RENT_UN_AVAILABLE, "不可出租");
            put(TRANSFERED, "已划拨");
            put(PRIVATE_USE, "自用");
            put(SELLED, "已出售");
            put(REMOVEED, "已拆迁");
            put(TEAR_DOWN, "已拆除");
            put(LOST, "已灭失");
        }
    };
    //1:提交申请,2:正在申请,3:申请通过
    public static final Map<String, String> NEATEN_MAP = new HashMap<String, String>() {
        {
            put("1", "提交申请");
            put("2", "正在申请");
            put("3", "申请通过");
        }
    };
    //0:无,1:有
    public static final Map<String, String> HIDDEN_MAP = new HashMap<String, String>() {
        {
            put("0", "无");
            put("1", "有");
        }
    };
    public static final Map<Integer, String> ROW_TYPE_MAP = new HashMap<Integer, String>() {
        {
            put(1, " varchar(50) null ");
            put(2, " int ");
            put(3, " bigint ");
            put(4, " int ");
        }
    };
}
