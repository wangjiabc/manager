package com.voucher.manage2.service.impl;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.constant.MenuConstant;
import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.tkmapper.entity.Menu;
import com.voucher.manage2.tkmapper.entity.RoomFile;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import com.voucher.manage2.tkmapper.mapper.RoomFileMapper;
import com.voucher.manage2.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoomFileMapper roomFileMapper;

    @Override
    public Object selectMenu(String parentGuid, String roomGuid) {
        List<Object> menuTree = new ArrayList<>();
        //总共有几级菜单
        //将菜单an等级分类
        Map<String, List<Menu>> levelMap = new TreeMap<>();
        Menu menuCondition = new Menu();
        menuCondition.setRootGuid(parentGuid);
        List<Menu> menus = menuMapper.select(menuCondition);
        for (Menu menu : menus) {
            List<Menu> menuList = levelMap.get(menu.getParentGuid());
            if (menuList == null) {
                menuList = new ArrayList<>();
                levelMap.put(menu.getParentGuid(), menuList);
            }
            menuList.add(menu);
        }
        //文件按菜单分类
        RoomFile roomFileCondition = new RoomFile();
        roomFileCondition.setRoomGuid(roomGuid);
        List<RoomFile> roomFiles = roomFileMapper.select(roomFileCondition);
        //所有分类下的文件
        Map<String, List<String>> roomFileMap = new HashMap<>(16);
        for (RoomFile roomFile : roomFiles) {
            List<String> roomFileList = roomFileMap.get(roomFile.getMenuGuid());
            if (roomFileList == null) {
                roomFileList = new ArrayList<>();
                roomFileMap.put(roomFile.getMenuGuid(), roomFileList);
            }
            //存文件guid+名字
            roomFileList.add(roomFile.getFileGuid());
        }
        putMenuList(menuTree, parentGuid, levelMap, roomFileMap);
        return menuTree;
    }

    private void putMenuList(List<Object> menuTree, String parentGuid, Map<String, List<Menu>> levelMap, Map<String, List<String>> roomFileMap) {
        //当前菜单
        List<Menu> menus = levelMap.get(parentGuid);
        if (menus == null) {
            return;
        } else {
            menuTree.add(menus);
            for (Menu menu : menus) {
                List<Object> list = new ArrayList<>();
                menuTree.add(list);
                putMenuList(list, menu.getGuid(), levelMap, roomFileMap);
            }
        }
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
