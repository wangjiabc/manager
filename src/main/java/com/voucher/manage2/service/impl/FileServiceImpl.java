package com.voucher.manage2.service.impl;

import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.service.FileService;
import com.voucher.manage2.tkmapper.entity.ElectronicFile;
import com.voucher.manage2.tkmapper.mapper.ElectronicFileMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lz
 * @description
 * @date 2019/5/20
 */
public class FileServiceImpl implements FileService {

    @Autowired
    private ElectronicFileMapper electronicFileMapper;
    ElectronicFile electronicFile = new ElectronicFile();
    @Override
    public List<ElectronicFile> selectElectronicFile() {

        Integer result = electronicFileMapper.selectCount(electronicFile);
        if(result == 0){
            ElectronicFile electronicFile1 = new ElectronicFile();
            electronicFile1.setGuid(UUID.randomUUID().toString());
            electronicFile1.setName(FileConstant.IMAGE.name);
            electronicFile1.setLevel(1);
            electronicFile1.setParentGuid("");

            ElectronicFile electronicFile2 = new ElectronicFile();
            electronicFile2.setGuid(UUID.randomUUID().toString());
            electronicFile2.setName(FileConstant.EXCEL.name);
            electronicFile2.setLevel(1);
            electronicFile2.setParentGuid("");

            ElectronicFile electronicFile3 = new ElectronicFile();
            electronicFile3.setGuid(UUID.randomUUID().toString());
            electronicFile3.setName(FileConstant.WORD.name);
            electronicFile3.setLevel(1);
            electronicFile3.setParentGuid("");

            ElectronicFile electronicFile4 = new ElectronicFile();
            electronicFile4.setGuid(UUID.randomUUID().toString());
            electronicFile4.setName(FileConstant.OTHER.name);
            electronicFile4.setLevel(1);
            electronicFile4.setParentGuid("");

            List<ElectronicFile> list = new ArrayList<>();
            list.add(electronicFile1);
            list.add(electronicFile2);
            list.add(electronicFile3);
            list.add(electronicFile4);

            Integer insertResult = electronicFileMapper.insertList(list);
            System.out.println("insertResult====="+insertResult);
        }

        List<ElectronicFile> electronic = electronicFileMapper.selectAll();

        return electronic;
    }
}
