package com.voucher.manage2.service;

import com.voucher.manage2.tkmapper.entity.RoomIn;

import java.util.List;

/**
 * @author lz
 * @description 资产操作
 * @date 2019/6/3
 */
public interface RoomService {
    /**
     * @Author lz
     * @Description: 资产入账
     * @param: []
     * @return: {java.lang.Integer}
     * @Date: 2019/6/3 15:36
     **/
    Integer roomIn(List<RoomIn> roomIn);
}
