package com.voucher.manage2.controller;

import com.voucher.manage2.constant.MenuConstant;
import com.voucher.manage2.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object selectMenu(String roomGuid) {
        return menuService.selectMenu(MenuConstant.FILE_ROOT_GUID,roomGuid);
    }

    @RequestMapping(value = "/insertMenu")
    public Integer insertMenu(@RequestBody Map<String, Object> jsonMap) {
        //log.debug("insertMenu===" + jsonMap);
        return menuService.insertMenu(jsonMap);
    }

    @RequestMapping(value = "/delMenu")
    public Integer delMenu(@RequestBody Map<String, Object> jsonMap) {
        //log.debug("insertMenu===" + jsonMap);
        return menuService.delMenu(jsonMap);
    }

    @RequestMapping(value = "/updateMenu")
    public Integer updateMenu(@RequestBody Map<String, Object> jsonMap) {
        //log.debug("insertMenu===" + jsonMap);
        return menuService.updateMenu(jsonMap);
    }
}
