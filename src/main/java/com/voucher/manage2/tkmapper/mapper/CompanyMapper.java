package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.Company;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CompanyMapper extends Mapper<Company>, InsertListMapper<Company> {

}