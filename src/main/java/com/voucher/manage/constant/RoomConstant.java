package com.voucher.manage.constant;

import java.util.HashMap;
import java.util.Map;

public class RoomConstant {
    //1:营业房,2:住宅房,3土地
    public static final Map<String, String> propertyMap = new HashMap<String, String>() {
        {
            put("1", "营业房");
            put("2", "住宅房");
            put("3", "土地");
        }
    };
    //1:已出租.2:空置,3:不可出租,4已划拨
    public static final Map<String, String> stateMap = new HashMap<String, String>() {
        {
            put("1", "已出租");
            put("2", "空置");
            put("3", "不可出租");
            put("4", "已划拨");
        }
    };
    //1:提交申请,2:正在申请,3:申请通过
    public static final Map<String, String> neatenMap = new HashMap<String, String>() {
        {
            put("1", "提交申请");
            put("2", "正在申请");
            put("3", "申请通过");
        }
    };
    //0:无,1:有
    public static final Map<String, String> hiddenMap = new HashMap<String, String>() {
        {
            put("0", "无");
            put("1", "有");
        }
    };
}
