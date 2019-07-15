package com.voucher.manage2.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.service.CompanyService;
import com.voucher.manage2.service.UserService;
import com.voucher.manage2.tkmapper.entity.Company;
import com.voucher.manage2.tkmapper.entity.SysUser;
import com.voucher.manage2.tkmapper.mapper.CompanyMapper;
import com.voucher.manage2.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author zsc
 * @description
 * @date 2019/7/12
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;
    @Autowired
    private UserService userService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Company company, SysUser sysUser) {
        List<Company> companies = companyMapper.selectAll();
        List<Integer> list = new ArrayList<>();
        for (Company company1 : companies) {
            String guid = company1.getGuid();
            list.add(Integer.parseInt(guid));
        }
        list.sort(Comparator.comparingInt(e -> -e));
        Integer guid = list.get(0);
        String uuid = (guid + 1) + "";
        company.setGuid(uuid);
        long time = System.currentTimeMillis();
        company.setCreateTime(time);
        companyMapper.insert(company);
        sysUser.setCompanyGuid(company.getGuid());
        userService.regist(sysUser);
    }

    @Override
    public List<Company> findAll() {
        return companyMapper.selectAll();
    }

    @Override
    public void updateOne(Company company) {
        Weekend<Company> companyWeekend = new Weekend<>(Company.class);
        companyWeekend.weekendCriteria().andEqualTo(Company::getGuid, company.getGuid());
        companyMapper.updateByExampleSelective(company, companyWeekend);
    }
}
