package com.voucher.manage2.controller;

import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.tkmapper.entity.Menu;
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
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/selectFileMenu")
    public List<Menu> selectMenu(){
        String str = null;
        return menuService.selectMenu(str);
    }

    @RequestMapping(value = "/insertMenu")
    public Integer insertMenu(@RequestBody Map<String, Object> jsonMap){
        System.out.println("insertMenu==="+jsonMap);
        return menuService.insertMenu(jsonMap);
    }

    @RequestMapping(value = "/delMenu")
    public Integer delMenu(@RequestBody Map<String,Object> jsonMap){
        System.out.println("delMenu==="+jsonMap);
        return menuService.delMenu(jsonMap);
    }

    @RequestMapping(value = "/updateMenu")
    public Integer updateMenu(@RequestBody Map<String,Object> jsonMap){
        System.out.println("updateMenu===="+jsonMap);
        return menuService.updateMenu(jsonMap);
    }
}
