package com.voucher.manage2.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.msg.Message;
import com.voucher.manage2.service.FileService;
import com.voucher.manage2.tkmapper.entity.FileRoom;
import com.voucher.manage2.tkmapper.entity.UploadFile;
import com.voucher.manage2.tkmapper.mapper.FileRoomMapper;
import com.voucher.manage2.tkmapper.mapper.UploadFileMapper;
import com.voucher.manage2.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FileServiceImpl implements FileService {
    @Autowired
    private UploadFileMapper uploadFileMapper;
    @Autowired
    private FileRoomMapper fileRoomMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> fileUpload(String realPath, MultipartFile file, List<String> roomGuids) {
        try {

            String fileName = IdUtil.simpleUUID() + "_" + file.getOriginalFilename();
            //文件后缀名
            String suffixName = FileTypeUtil.getType(file.getInputStream());
            //文件类型
            Integer fileType = FileUtils.getFileType(suffixName);
            //文件类型名
            String fileTypeName = FileUtils.getFileTypeName(fileType);
            //保存
            realPath = realPath + File.separator + fileTypeName;
            String path = realPath + File.separator + fileName;
            System.out.println("+++++++++" + path);
            File realPathFile = new File(realPath);
            if (!realPathFile.exists()) {
                realPathFile.mkdirs();
            }
            file.transferTo(new File(path));
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

            //返回图片地址

        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(Message.FILE_UPLOAD_FAILED, e);
        }
        return null;
    }
}
