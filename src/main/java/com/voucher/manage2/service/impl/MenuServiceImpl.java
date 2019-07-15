package com.voucher.manage2.service.impl;

import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.service.MenuService;
import com.voucher.manage2.tkmapper.entity.RoomFile;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import com.voucher.manage2.tkmapper.mapper.RoomFileMapper;
import com.voucher.manage2.utils.CommonUtils;
import com.voucher.manage2.utils.FileUtils;
import com.voucher.manage2.utils.MapUtils;
import com.voucher.manage2.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoomFileMapper roomFileMapper;

    @Override
    public MenuDTO selectFileMenu(MenuDTO rootMenu, String[] roomGuids) {
        //将用户的菜单按级别分类
        Map<String, List<MenuDTO>> levelMap = getLevelMap(rootMenu);
        //所有分类下的文件
        Map<String, List<Map<String, String>>> menuFileMap = null;
        //当有roomGuids时将文件查出挂在叶子节点上
        if (ObjectUtils.isNotEmpty(roomGuids)) {
            menuFileMap = getRoomFileList(roomGuids);
        }
        //构造树形菜单
        putMenuAndFileList(rootMenu, levelMap, menuFileMap);
        return rootMenu;
    }

    private Map<String, List<MenuDTO>> getLevelMap(MenuDTO rootMenu) {
        Map<String, List<MenuDTO>> levelMap = new HashMap(32);
        MenuDTO menuCondition = new MenuDTO();
        menuCondition.setRootGuid(rootMenu.getGuid());
        menuCondition.setDel(false);
        List<MenuDTO> menus = menuMapper.select(menuCondition);
        for (MenuDTO menu : menus) {
            List<MenuDTO> menuList = levelMap.get(menu.getParentGuid());
            if (menuList == null) {
                menuList = new ArrayList<>();
                levelMap.put(menu.getParentGuid(), menuList);
            }
            menuList.add(menu);
        }
        return levelMap;
    }

    private Map<String, List<Map<String, String>>> getRoomFileList(String[] roomGuids) {
        //获取room下的所有文件列表
        Map<String, List<Map<String, String>>> menuFileMap;
        Example example = new Example(RoomFile.class);
        example.createCriteria().andIn("roomGuid", Arrays.asList(roomGuids));
        List<RoomFile> roomFiles = roomFileMapper.selectByExample(example);
        //文件按菜单分类

        menuFileMap = new HashMap<>(16);
        for (RoomFile roomFile : roomFiles) {
            List<Map<String, String>> roomFileList = menuFileMap.get(roomFile.getMenuGuid());
            if (roomFileList == null) {
                roomFileList = new ArrayList<>();
                menuFileMap.put(roomFile.getMenuGuid(), roomFileList);
            }
            //fileGuid是文件guid+名字,即存的文件名
            String fileGuid = roomFile.getFileGuid();
            HashMap<String, String> map = new HashMap<>(8);
            String downLoadName = FileUtils.getDownLoadName(fileGuid);
            map.put("name", downLoadName);
            map.put("url", FileUtils.getFileUrlPath(fileGuid));
            map.put("type", FileUtils.isImage(downLoadName.substring(downLoadName.lastIndexOf(".") + 1)) + "");
            roomFileList.add(map);
        }
        return menuFileMap;
    }

    private void putMenuAndFileList(MenuDTO menuDTO, Map<String, List<MenuDTO>> levelMap, Map<String, List<Map<String, String>>> menuFileMap) {
        //当前菜单
        List<MenuDTO> menus = levelMap.get(menuDTO.getGuid());
        if (menus == null) {
            //menus为空代表是叶子节点
            if (ObjectUtils.isNotEmpty(menuFileMap)) {
                //挂载文件
                menuDTO.setFiles(menuFileMap.get(menuDTO.getGuid()));
            }
            return;
        } else {
            menuDTO.setChildList(menus);
            for (MenuDTO menu : menus) {
                putMenuAndFileList(menu, levelMap, menuFileMap);
            }
        }
    }

    @Override
    public Integer insertMenu(MenuDTO menu) {
        return menuMapper.insertSelective(menu);
    }

    @Override
    public Integer updateMenu(Map<String, Object> jsonMap) {
        MenuDTO menu = new MenuDTO();
        menu.setName(MapUtils.getString("name", jsonMap));
        menu.setRequired(MapUtils.getBoolean("required", jsonMap));
        Example example = new Example(MenuDTO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("guid", MapUtils.getString("guid", jsonMap));
        return menuMapper.updateByExampleSelective(menu, example);
    }

    @Override
    public List<MenuDTO> selectAllRootMenu() {
        ArrayList<MenuDTO> result = new ArrayList<>();
        MenuDTO menuCondition = new MenuDTO();
        menuCondition.setLevel(0);
        menuCondition.setDel(false);
        List<MenuDTO> rootMenus = menuMapper.select(menuCondition);
        for (MenuDTO rootMenu : rootMenus) {
            result.add(menuService.selectFileMenu(rootMenu, null));
        }
        return result;
    }

    @Override
    public Integer delLeafMenu(List<String> leafGuids) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setDel(true);
        Example example = new Example(MenuDTO.class);
        example.createCriteria().andIn("guid", leafGuids);
        return menuMapper.updateByExampleSelective(menuDTO, example);
    }

}
