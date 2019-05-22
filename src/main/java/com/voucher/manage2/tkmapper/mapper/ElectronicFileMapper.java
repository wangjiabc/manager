package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.ElectronicFile;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ElectronicFileMapper extends Mapper<ElectronicFile>, InsertListMapper<ElectronicFile> {
}