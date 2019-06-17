package com.voucher.manage2.controller;

import com.voucher.manage2.dto.SysUserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lz
 * @description
 * @date 2019/6/11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 注册
     */
    @PostMapping("regist")
    public void insertUserInfo() {

    }

    @PostMapping("login")
    public String login(SysUserDTO sysUser) {
        //登录
        //获取tokenID
        //将用户存入redis
        return null;
    }
}
