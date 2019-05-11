package com.voucher.manage.controller;


import com.alibaba.fastjson.JSONArray;
import com.rmi.server.Server;
import com.voucher.manage.dao.AssetsDAO;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.daoModel.Test;
import com.voucher.manage.model.Users;
import com.voucher.manage.service.UserService;
import com.voucher.sqlserver.context.Connect;
import com.voucher.sqlserver.context.ConnectRMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/test")
public class testController {

    //ApplicationContext applicationContext = new Connect().get();
    //
    //AssetsDAO assetsDAO = (AssetsDAO) applicationContext.getBean("assetsdao");
    //
    //CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");
    @Autowired
    AssetsDAO assetsDAO;
    @Autowired
    CurrentDao currentDao;


    Server server = new ConnectRMI().get();

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "get")
    public @ResponseBody
    List get() throws Exception {
        Room room = new Room();
        room.setLimit(10);
        room.setOffset(1);
        room.setNotIn("id");
        long start = System.currentTimeMillis();
        List list = assetsDAO.getRoom(room);
        long end = System.currentTimeMillis();
        System.out.println("耗时++++++++++++++++" + (end - start));
        return list;

    }

    @RequestMapping(value = "getUser")
    public @ResponseBody
    Users getUser() throws Exception {

        return userService.getUserByOnlyOpenId("aaa");

    }

    @RequestMapping(value = "insert")
    public @ResponseBody
    Integer insert() throws Exception {

        currentDao.alterTable(true, "test", "bbb", "");

        return 1;

    }

    @RequestMapping(value = "insertTable")
    public @ResponseBody
    Integer insertTable() throws Exception {

        Test test = new Test();

        test.setGuid("aaaaaaaaa");

        JSONArray jsonArray = new JSONArray();

        jsonArray.add("guid");
        jsonArray.add("aaaaaaaaa");
        jsonArray.add("item_f7b77bb1b9640700291ddaa4222ae9b5");
        jsonArray.add("nnn");


        currentDao.insertTable(test, jsonArray.toJSONString());

        return 1;

    }

    @RequestMapping(value = "select")
    public @ResponseBody
    Map select(Integer limit, Integer page) throws Exception {

        Room room = new Room();
        room.setLimit(limit);
        room.setOffset((page - 1) * limit);
        room.setNotIn("id");
        String[] where = {"room.del=", "false"};
        room.setWhere(where);
        //room.setWhereTerm();
        Map map = null;
        try {
            long start = System.currentTimeMillis();
            map = currentDao.selectTable(room);
            long end = System.currentTimeMillis();
            System.out.println("耗时++++++++++++++++" + (end - start));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;

    }

}
