package com.voucher.manage2.controller;


import com.voucher.manage2.service.CompanyService;
import com.voucher.manage2.tkmapper.entity.Company;
import com.voucher.manage2.tkmapper.entity.SysUser;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author zsc
 * @description
 * @date 2019/7/12
 */
@RestController
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    /**
     * 存储公司信息
     * @param jsonMap
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("/save")
    public void saveCompany(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        Company company = new Company();
        SysUser sysUser = new SysUser();
        BeanUtils.populate(company,jsonMap);
        BeanUtils.populate(sysUser,jsonMap);
        companyService.save(company,sysUser);
    }

    /**
     * 返回所有信息
     * @return
     */
    @RequestMapping("/findAll")
    public List<Company> findAll(){
        return companyService.findAll();
    }

    /**
     * 修改某个公司信息
     * @param jsonMap
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("/update")
    public void updateCompany(@RequestBody Map<String, Object> jsonMap) throws InvocationTargetException, IllegalAccessException {
        Company company = new Company();
        BeanUtils.populate(company,jsonMap);
        companyService.updateOne(company);
    }

}
