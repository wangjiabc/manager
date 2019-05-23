package com.voucher.manage2.service.impl;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.constant.MenuConstant;
import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.tkmapper.entity.Menu;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import com.voucher.manage2.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenu(String parentGuid) {

        Menu menu = new Menu();
        menu.setParentGuid(parentGuid);

        return menuMapper.select(menu);
//        if (result == 0) {
//            Menu menu1 = new Menu();
//            menu1.setGuid(IdUtil.simpleUUID());
//            menu1.setName(FileConstant.IMAGE.typeName);
//            menu1.setLevel(MenuConstant.level1);
//            menu1.setParentGuid("");
//
//            Menu menu2 = new Menu();
//            menu2.setGuid(IdUtil.simpleUUID());
//            menu2.setName(FileConstant.EXCEL.typeName);
//            menu2.setLevel(MenuConstant.level1);
//            menu2.setParentGuid("");
//
//            Menu menu3 = new Menu();
//            menu3.setGuid(UUID.randomUUID().toString());
//            menu3.setName(FileConstant.WORD.typeName);
//            menu3.setLevel(MenuConstant.level1);
//            menu3.setParentGuid("");
//
//            Menu menu4 = new Menu();
//            menu4.setGuid(UUID.randomUUID().toString());
//            menu4.setName(FileConstant.OTHER.typeName);
//            menu4.setLevel(MenuConstant.level1);
//            menu4.setParentGuid("");
//
//            List<Menu> list = new ArrayList<>();
//            list.add(menu1);
//            list.add(menu2);
//            list.add(menu3);
//            list.add(menu4);
//
//            Integer insertResult = menuMapper.insertList(list);
//            System.out.println("insertResult=====" + insertResult);
//        }

//        List<Menu> menus = menuMapper.selectAll();
//
//        return menus;
    }

    @Override
    public Integer insertMenu(Map<String, Object> jsonMap) {
        Menu menu = new Menu();
        menu.setGuid(IdUtil.simpleUUID());
        menu.setLevel(MapUtils.getInteger("level", jsonMap) + 1);
        menu.setName(MapUtils.getString("name", jsonMap));
        menu.setParentGuid(MapUtils.getString("parentGuid", jsonMap));

        Integer result = menuMapper.insertSelective(menu);
        System.out.println("insertMenu===" + result);
        return result;
    }

    @Override
    public Integer delMenu(Map<String, Object> jsonMap) {
        Menu menu = new Menu();
        menu.setGuid(MapUtils.getString("guid", jsonMap));
        Integer result = menuMapper.delete(menu);
        System.out.println("delMenu===" + result);
        return result;
    }

    @Override
    public Integer updateMenu(Map<String, Object> jsonMap) {
        Menu menu = new Menu();
        menu.setName(MapUtils.getString("name", jsonMap));

        menu.setGuid(MapUtils.getString("guid", jsonMap));
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("guid", menu.getGuid());
        Integer result = menuMapper.updateByExampleSelective(menu, example);
        System.out.println("updateMenu====" + result);
        return result;
    }

}
