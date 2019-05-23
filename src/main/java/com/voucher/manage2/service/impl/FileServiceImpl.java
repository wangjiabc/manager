package com.voucher.manage2.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.constant.MenuConstant;
import com.voucher.manage2.exception.FileUploadException;
import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.service.FileService;
import com.voucher.manage2.tkmapper.entity.RoomFile;
import com.voucher.manage2.tkmapper.entity.UploadFile;
import com.voucher.manage2.tkmapper.mapper.RoomFileMapper;
import com.voucher.manage2.tkmapper.mapper.UploadFileMapper;
import com.voucher.manage2.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.voucher.manage2.tkmapper.entity.Menu;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author lz
 * @description
 * @date 2019/5/20
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private UploadFileMapper uploadFileMapper;
    @Autowired
    private RoomFileMapper roomFileMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String fileUpload(MultipartFile file, List<String> roomGuids) {

        String fileName = IdUtil.simpleUUID() + "_" + file.getOriginalFilename();
        File tarFile = null;
        try {
            //文件后缀名
            String suffixName = FileTypeUtil.getType(file.getInputStream());
            //文件类型
            Integer fileType = FileUtils.getFileType(suffixName);
            //保存
            //realPath = realPath + File.separator + fileTypeName;
            //问价保存路径
            String filePath = FileUtils.getFilePath(fileType);
            File realPathFile = new File(filePath);
            if (!realPathFile.exists()) {
                realPathFile.mkdirs();
            }
            //文件全对象名
            String path = filePath + File.separator + fileName;
            System.out.println("+++++++++" + path);
            //保存的文件对象
            tarFile = new File(path);
            file.transferTo(tarFile);
            //TODO 类型是图片就压缩
            //if (FileUtils.isImage(type)) {
            //
            //}
            //文件入库
            UploadFile uploadFile = new UploadFile();
            String fileGuid = IdUtil.simpleUUID();
            uploadFile.setGuid(fileGuid);
            uploadFile.setType(fileType);
            uploadFile.setUploadTime(System.currentTimeMillis());
            uploadFile.setUrl(path);
            uploadFileMapper.insert(uploadFile);
            //文件资产关系入库
            List<RoomFile> roomFiles = roomGuids.stream().map(e -> {
                RoomFile roomFile = new RoomFile();
                roomFile.setFileGuid(fileGuid);
                roomFile.setRoomGuid(e);
                return roomFile;
            }).collect(Collectors.toList());
            roomFileMapper.insertList(roomFiles);
        } catch (Exception e) {
            log.warn("文件入库异常!", e);
            if (tarFile.exists()) {
                tarFile.delete();
            }
            throw new FileUploadException(file.getOriginalFilename(), e);
        }
        //返回文件名
        return fileName;
    }
}
