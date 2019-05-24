package com.voucher.manage2.dto;

import com.voucher.manage2.tkmapper.entity.Menu;

import javax.persistence.Table;
import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/5/24
 */
@Table(name = "[menu]")
public class MenuDTO extends Menu {
    private List<MenuDTO> childList;
    //叶子节点才有文件
    private List<String> fileNames;

    public List<MenuDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuDTO> childList) {
        this.childList = childList;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}
