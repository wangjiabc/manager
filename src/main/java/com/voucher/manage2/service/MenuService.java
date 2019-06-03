package com.voucher.manage2.service;

import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.tkmapper.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    MenuDTO selectMenu(MenuDTO rootMenu, String[] roomGuids);

    Integer insertMenu(MenuDTO menu);

    Integer delMenu(Map<String, Object> jsonMap);

    Integer updateMenu(Map<String, Object> jsonMap);

    List<MenuDTO> selectAllRootMenu();
}
