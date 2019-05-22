package com.voucher.manage2.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.exception.FileUploadException;
import com.voucher.manage2.msg.Message;
import com.voucher.manage2.msg.MessageBean;
import com.voucher.manage2.service.FileService;
import com.voucher.manage2.tkmapper.entity.FileRoom;
import com.voucher.manage2.tkmapper.entity.UploadFile;
import com.voucher.manage2.tkmapper.mapper.FileRoomMapper;
import com.voucher.manage2.tkmapper.mapper.UploadFileMapper;
import com.voucher.manage2.utils.FileUtils;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    private FileRoomMapper fileRoomMapper;

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
            List<FileRoom> fileRooms = roomGuids.stream().map(e -> {
                FileRoom fileRoom = new FileRoom();
                fileRoom.setFileGuid(fileGuid);
                fileRoom.setRoomGuid(e);
                return fileRoom;
            }).collect(Collectors.toList());
            fileRoomMapper.insertList(fileRooms);
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
