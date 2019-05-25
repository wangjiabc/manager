package com.voucher.manage2.service.impl;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.tkmapper.entity.Menu;
import com.voucher.manage2.tkmapper.entity.RoomFile;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import com.voucher.manage2.tkmapper.mapper.RoomFileMapper;
import com.voucher.manage2.utils.FileUtils;
import com.voucher.manage2.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoomFileMapper roomFileMapper;

    @Override
    public Object selectMenu(String parentGuid, String roomGuid) {
        //List<Object> menuTree = new ArrayList<>();
        MenuDTO menuDTO = new MenuDTO();
        //总共有几级菜单
        //将菜单an等级分类
        Map<String, List<MenuDTO>> levelMap = new TreeMap<>();
        MenuDTO menuCondition = new MenuDTO();
        menuCondition.setRootGuid(parentGuid);
        List<MenuDTO> menus = menuMapper.select(menuCondition);
        for (MenuDTO menu : menus) {
            List<MenuDTO> menuList = levelMap.get(menu.getParentGuid());
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
        Map<String, List<Map<String, String>>> menuFileMap = new HashMap<>(16);
        for (RoomFile roomFile : roomFiles) {
            List<Map<String, String>> roomFileList = menuFileMap.get(roomFile.getMenuGuid());
            if (roomFileList == null) {
                roomFileList = new ArrayList<>();
                menuFileMap.put(roomFile.getMenuGuid(), roomFileList);
            }
            //fileGuid是文件guid+名字,即存的文件名
            String fileGuid = roomFile.getFileGuid();
            HashMap<String, String> map = new HashMap<>(4);
            map.put("name", FileUtils.getDownLoadName(fileGuid));
            map.put("url", FileUtils.getFileUrlPath(fileGuid));
            roomFileList.add(map);
        }
        putMenuList(menuDTO, parentGuid, levelMap, menuFileMap);
        return menuDTO;
    }

    private void putMenuList(MenuDTO menuDTO, String parentGuid, Map<String, List<MenuDTO>> levelMap, Map<String, List<Map<String, String>>> menuFileMap) {
        //当前菜单
        List<MenuDTO> menus = levelMap.get(parentGuid);
        if (menus == null) {
            //menus为空代表是叶子节点
            menuDTO.setFiles(menuFileMap.get(menuDTO.getGuid()));
            return;
        } else {
            menuDTO.setChildList(menus);
            for (MenuDTO menu : menus) {
                putMenuList(menu, menu.getGuid(), levelMap, menuFileMap);
            }
        }
    }

    @Override
    public Integer insertMenu(Map<String, Object> jsonMap) {
        MenuDTO menu = new MenuDTO();
        menu.setGuid(IdUtil.simpleUUID());
        menu.setLevel(MapUtils.getInteger("level", jsonMap));
        menu.setRootGuid(MapUtils.getString("rootGuid", jsonMap));
        menu.setName(MapUtils.getString("name", jsonMap));
        menu.setParentGuid(MapUtils.getString("parentGuid", jsonMap));

        Integer result = menuMapper.insert(menu);
        log.debug(String.valueOf(result));
        return result;
    }

    @Override
    public Integer delMenu(Map<String, Object> jsonMap) {
        MenuDTO menu = new MenuDTO();
        menu.setGuid(MapUtils.getString("guid", jsonMap));
        Integer result = menuMapper.delete(menu);
        log.debug(String.valueOf(result));
        return result;
    }

    @Override
    public Integer updateMenu(Map<String, Object> jsonMap) {
        MenuDTO menu = new MenuDTO();
        menu.setName(MapUtils.getString("name", jsonMap));

        menu.setGuid(MapUtils.getString("guid", jsonMap));
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("guid", menu.getGuid());
        Integer result = menuMapper.updateByExampleSelective(menu, example);
        log.debug(String.valueOf(result));
        return result;
    }

}
