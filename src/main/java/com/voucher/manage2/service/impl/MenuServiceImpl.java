package com.voucher.manage2.service.impl;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.constant.MenuConstant;
import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.tkmapper.entity.Menu;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import com.voucher.manage2.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenu(String parentGuid) {

        Menu menu = new Menu();
        menu.setParentGuid(parentGuid);

        List<Menu> select = menuMapper.select(menu);

        return null;
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
