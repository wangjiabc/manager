package com.voucher.manage2.service;

import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.tkmapper.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    MenuDTO selectMenuByRootGuid(MenuDTO rootMenu, String[] roomGuids);

    Integer insertMenu(MenuDTO menu);

    Integer updateMenu(Map<String, Object> jsonMap);

    List<MenuDTO> selectAllRootMenu();

    Integer delLeafMenu(List<String> leafGuids);
}
