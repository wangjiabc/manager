package com.voucher.manage2.service;

import com.voucher.manage2.tkmapper.entity.Company;
import com.voucher.manage2.tkmapper.entity.SysUser;

import java.util.List;

/**
 * @author zsc
 * @description
 * @date 2019/7/12
 */
public interface CompanyService {
    /**
     * 注册时存储公司信息
     */
    void save(Company company,SysUser sysUser);

    /**
     * 查询表中所有数据
     * @return
     */
    List<Company> findAll();

    /**
     * 修改一条数据
     * @param company
     */
    void updateOne(Company company);
}
