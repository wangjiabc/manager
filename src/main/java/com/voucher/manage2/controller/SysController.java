package com.voucher.manage2.controller;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.service.SysService;
import com.voucher.manage2.tkmapper.entity.*;
import com.voucher.manage2.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lz
 * @description
 * @date 2019/6/10
 */
@RestController
@RequestMapping("/sys")
public class SysController {
    @Autowired
    private SysService sysService;
    private final Pattern urlPattern = Pattern.compile("^/[a-z]+/[a-z]+\\.do$");

    @RequestMapping("addUrls")
    public Integer addUrls(@RequestBody Map<String, Object> jsonMap) {
        List<Map<String, Object>> urls = (List<Map<String, Object>>) jsonMap.get("urls");
        List<SysUrl> sysUrls = new ArrayList<>();
        for (Map<String, Object> url : urls) {
            String urlStr = MapUtils.getString("url", url);
            if (urlPattern.matcher(urlStr).find()) {
                SysUrl sysUrl = new SysUrl();
                sysUrl.setName(MapUtils.getString("name", url));
                //^/[a-z]+/[a-z]+\.do$
                sysUrl.setUrl(urlStr);
                sysUrls.add(sysUrl);
            } else {
                throw BaseException.getDefault("url非法,示例:/aa/bb.do");
            }
        }
        return sysService.addUrls(sysUrls);
    }

    @RequestMapping("getUrls")
    public List<SysUrl> getUrls(String urlCondition) {
        return sysService.getUrls(urlCondition);
    }

    @RequestMapping("addModel")
    public Integer addModel(@RequestBody Map<String, Object> jsonMap) {
        SysModel sysModel = new SysModel();
        sysModel.setGuid(IdUtil.simpleUUID());
        sysModel.setName(MapUtils.getString("name", jsonMap));
        return sysService.addModel(sysModel);
    }

    @RequestMapping("addModelUrls")
    public Integer addModelUrls(@RequestBody Map<String, Object> jsonMap) {
        List<String> urls = MapUtils.getStrList("urls", jsonMap);
        List<SysModelUrl> modelUrls = new ArrayList<>();
        String modelGuid = MapUtils.getString("modelGuid", jsonMap);
        for (String url : urls) {
            SysModelUrl sysModelUrl = new SysModelUrl();
            sysModelUrl.setModelGuid(modelGuid);
            sysModelUrl.setUrl(url);
            modelUrls.add(sysModelUrl);
        }
        return sysService.addModelUrls(modelUrls);
    }

    @RequestMapping("addRole")
    public Integer addRole(@RequestBody Map<String, Object> jsonMap) {
        SysRole sysRole = new SysRole();
        sysRole.setGuid(IdUtil.simpleUUID());
        sysRole.setName(MapUtils.getString("name", jsonMap));
        return sysService.addRole(sysRole);
    }

    @RequestMapping("addRoleModels")
    public Integer addRoleModels(@RequestBody Map<String, Object> jsonMap) {
        List<String> modelGuids = MapUtils.getStrList("modelGuids", jsonMap);
        List<SysRoleModel> roleModels = new ArrayList<>();
        String roleGuid = MapUtils.getString("roleGuid", jsonMap);
        for (String modelGuid : modelGuids) {
            SysRoleModel sysRoleModel = new SysRoleModel();
            sysRoleModel.setModelGuid(modelGuid);
            sysRoleModel.setRoleGuid(roleGuid);
            roleModels.add(sysRoleModel);
        }
        return sysService.addRoleModels(roleModels);
    }

    @RequestMapping("addRoleMenus")
    public Integer addRoleMenus(@RequestBody Map<String, Object> jsonMap) {
        List<String> menuGuids = MapUtils.getStrList("menuGuids", jsonMap);
        List<SysRoleMenu> roleModels = new ArrayList<>();
        String roleGuid = MapUtils.getString("roleGuid", jsonMap);
        for (String menuGuid : menuGuids) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuGuid(menuGuid);
            sysRoleMenu.setRoleGuid(roleGuid);
            roleModels.add(sysRoleMenu);
        }
        return sysService.addRoleMenus(roleModels);
    }

    @RequestMapping("addUserRoles")
    public Integer addUserRoles(@RequestBody Map<String, Object> jsonMap) {
        List<String> roleGuids = MapUtils.getStrList("roleGuids", jsonMap);
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        String userGuid = MapUtils.getString("userGuid", jsonMap);
        for (String roleGuid : roleGuids) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserGuid(userGuid);
            sysUserRole.setRoleGuid(roleGuid);
            sysUserRoles.add(sysUserRole);
        }
        return sysService.addUserRoles(sysUserRoles);
    }

    @RequestMapping("addUserConditions")
    public Integer addUserConditions(@RequestBody Map<String, Object> jsonMap) {
        List<Map<String, Object>> conditions = (List<Map<String, Object>>) MapUtils.get("conditions", jsonMap);
        String userGuid = MapUtils.getString("userGuid", jsonMap);
        List<SysUserCondition> sysUserConditionArrayList = new ArrayList<>();
        for (Map<String, Object> condition : conditions) {
            SysUserCondition sysUserCondition = new SysUserCondition();
            sysUserCondition.setGuid(IdUtil.simpleUUID());
            sysUserCondition.setUserGuid(userGuid);
            sysUserCondition.setLineUuid(MapUtils.getString("lineUuid", condition));
            sysUserConditionArrayList.add(sysUserCondition);
            //sqlCondition.append("," + MapUtils.getString("lineUuid", condition) + "=" + MapUtils.getString("value", condition));
        }
        //sysUserCondition.setSqlCondition(sqlCondition.delete(0, 1).toString());
        return sysService.addUserConditions(sysUserConditionArrayList);
    }


}
