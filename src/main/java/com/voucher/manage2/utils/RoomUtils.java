package com.voucher.manage2.utils;

import cn.hutool.core.util.StrUtil;
import com.voucher.manage2.cache.SystemCache;
import com.voucher.manage2.constant.RoomConstant;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.tkmapper.entity.RoomIn;
import com.voucher.manage2.tkmapper.entity.RoomOut;

import java.util.Map;

/**
 * @author lz
 * @description 资产操作的工具类
 * @date 2019/6/4
 */
public class RoomUtils {
    /**
     * 资产操作记录说明模板
     */
    public static final String INTRODUCTION_TEMP = "操作人:{},操作类型:{},金额:{}元,时间:{}";

    public static String getRoomLogIntroduction(RoomIn roomIn) {
        //DateUtil.formatBetween()
        return StrUtil.format(INTRODUCTION_TEMP, CommonUtils.getCurrentUserName(), SystemCache.MENU_CACHE.get(roomIn.getTypeGuid()).getName(), roomIn.getMoney(), TimeUtils.formatTime(roomIn.getDate(),TimeUtils.exp2));
    }

    public static String getRoomLogIntroduction(RoomOut roomOut) {
        return StrUtil.format(INTRODUCTION_TEMP, CommonUtils.getCurrentUserName(), SystemCache.MENU_CACHE.get(roomOut.getTypeGuid()).getName(), roomOut.getMoney(), TimeUtils.formatTime(roomOut.getDate(),TimeUtils.exp2));
    }

    /**
     * @Author lz
     * @Description:出账时获取出账对应的资产状态
     * @param: [typeGuid]
     * @return: {java.lang.Integer}
     * @Date: 2019/6/5 16:04
     **/
    public static Integer getRoomStateByTypeGuid(String typeGuid) {
        Map<Integer, String> stateMap = RoomConstant.STATE_MAP;
        String typeName = SystemCache.MENU_CACHE.get(typeGuid).getName();
        for (Map.Entry<Integer, String> entry : stateMap.entrySet()) {
            if (entry.getValue().contains(typeName)) {
                return entry.getKey();
            }
        }
        throw BaseException.getDefault();
    }
}
