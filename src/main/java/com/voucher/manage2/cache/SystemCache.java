package com.voucher.manage2.cache;

import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lz
 * @description 系统缓存
 * @date 2019/6/4
 */
//@Component
public class SystemCache implements InitializingBean {
    /**
     * 菜单缓存guid->menu
     */
    public static final Map<String, MenuDTO> MENU_CACHE = new HashMap<>();
    private static MenuMapper menuMapper;

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        SystemCache.menuMapper = menuMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        innitMenuCache();
    }

    private void innitMenuCache() {
        List<MenuDTO> menuDTOS = menuMapper.selectAll();
        for (MenuDTO menuDTO : menuDTOS) {
            MENU_CACHE.put(menuDTO.getGuid(), menuDTO);
        }
    }
}
