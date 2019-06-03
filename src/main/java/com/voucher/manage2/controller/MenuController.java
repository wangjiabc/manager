package com.voucher.manage2.controller;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.constant.MenuConstant;
import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.tkmapper.entity.Menu;
import com.voucher.manage2.utils.CommonUtils;
import com.voucher.manage2.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wr
 * @description 菜单控制器
 * @date 2019/5/23
 */
@RestController
@RequestMapping("menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/selectFileMenu")
    public Object selectMenu(String[] roomGuids) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setGuid(MenuConstant.FILE_ROOT_GUID);
        return menuService.selectMenu(menuDTO, roomGuids);
    }

    @RequestMapping(value = "/selectAllRootMenu")
    public List<MenuDTO> selectMenu() {
        return menuService.selectAllRootMenu();
    }

    @RequestMapping(value = "/insertFileMenu")
    public Integer insertMenu(@RequestBody Map<String, Object> jsonMap) {
        log.debug("insertMenu===" + jsonMap);
        MenuDTO menu = new MenuDTO();
        menu.setGuid(IdUtil.simpleUUID());
        menu.setLevel(MapUtils.getInteger("level", jsonMap));
        menu.setRootGuid(MapUtils.getString("rootGuid", jsonMap));
        menu.setName(MapUtils.getString("name", jsonMap));
        menu.setParentGuid(MapUtils.getString("parentGuid", jsonMap));
        menu.setRequired(MapUtils.getBoole("required", jsonMap));
        return menuService.insertMenu(menu);
    }

    @GetMapping(value = "/insertRootMenu")
    public Integer insertRootMenu(String name) {
        MenuDTO menu = new MenuDTO();
        String rootGuid = IdUtil.simpleUUID();
        menu.setGuid(rootGuid);
        menu.setLevel(0);
        menu.setRootGuid(rootGuid);
        menu.setName(name);
        menu.setParentGuid(CommonUtils.getCurrentUserGuid());
        return menuService.insertMenu(menu);
    }


    @RequestMapping(value = "/delMenu")
    public Integer delMenu(@RequestBody Map<String, Object> jsonMap) {
        log.debug("insertMenu===" + jsonMap);
        return menuService.delMenu(jsonMap);
    }

    @RequestMapping(value = "/updateMenu")
    public Integer updateMenu(@RequestBody Map<String, Object> jsonMap) {
        log.debug("insertMenu===" + jsonMap);
        return menuService.updateMenu(jsonMap);
    }
}
