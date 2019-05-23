package com.voucher.manage2.service;

import com.voucher.manage2.tkmapper.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    List<Menu> selectMenu(String parentGuid);

    Integer insertMenu(Map<String, Object> jsonMap);

    Integer delMenu(Map<String, Object> jsonMap);

    Integer updateMenu(Map<String, Object> jsonMap);
}
