package com.voucher.manage2.controller;

import cn.hutool.crypto.SecureUtil;
import com.voucher.manage2.dto.SysUserDTO;
import com.voucher.manage2.redis.JedisUtil0;
import com.voucher.manage2.service.UserService;
import com.voucher.manage2.tkmapper.entity.SysUser;
import com.voucher.manage2.utils.MapUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lz
 * @description
 * @date 2019/6/11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("regist")
    public void insertUserInfo(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        //不能这么取值
        //public SysUser regist(SysUser sysUser) {
        SysUser sysUser = new SysUser();
        BeanUtils.populate(sysUser, jsonMap);
        userService.regist(sysUser);
    }

    @PostMapping("update")
    public void updateUser(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        SysUser sysUser = new SysUser();
        BeanUtils.populate(sysUser, jsonMap);
        userService.updateUser(sysUser);
    }

    @PostMapping("updatePassword")
    public Integer updatePassword(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        SysUser sysUser = new SysUser();
        BeanUtils.populate(sysUser, jsonMap);
        return userService.updatePassWord(sysUser, MapUtils.getString("newPassword", jsonMap));
    }

    @PostMapping("login")
    public Object login(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        //登录
        SysUserDTO sysUser = new SysUserDTO();
        BeanUtils.populate(sysUser, jsonMap);
        sysUser = userService.login(sysUser);
        //sysUser.
        //获取tokenID
        long currentTimeMillis = System.currentTimeMillis();
        String token = SecureUtil.md5(sysUser.getGuid() + currentTimeMillis);
        sysUser.setLastFreshTime(currentTimeMillis);
        //将用户存入redis
        JedisUtil0.setUserDTO(token, sysUser);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("roles", sysUser.roles);
        resultMap.put("urls", userService.userAllPermission(sysUser));
        return resultMap;
    }

    @RequestMapping("logout")
    public void logout() {

    }

    @PostMapping("getAll")
    public Object getAll() {
        return userService.getAllUser();
    }

}
