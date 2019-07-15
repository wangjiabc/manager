package com.voucher.manage2.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lz
 * @description
 * @date 2019/7/15
 */
public enum RoomLogConstant {
    /**
     * 入账类型
     **/
    IN(1, "24fc93f570f447b5b0eca6fd0b9e89e7"),
    OUT(2, "37538c55f7bb4895a3d24b739890f814");
    //日志的大类类型
    public Integer type;
    //类型对应的根菜单
    public String menuGuid;

    RoomLogConstant(Integer type, String menuGuid) {
        this.type = type;
        this.menuGuid = menuGuid;
    }

    public static final Map<Integer, String> TYPE_MENU_GUID = new HashMap<Integer, String>() {
        {
            for (RoomLogConstant roomLogConstant : RoomLogConstant.values()) {
                put(roomLogConstant.type, roomLogConstant.menuGuid);
            }
        }
    };
}
