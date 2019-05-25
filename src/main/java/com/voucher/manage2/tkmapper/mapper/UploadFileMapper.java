package com.voucher.manage2.tkmapper.mapper;

import com.voucher.manage2.tkmapper.entity.UploadFile;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface UploadFileMapper extends Mapper<UploadFile>, InsertListMapper<UploadFile> {
}