package com.voucher.manage2.dto;

import com.voucher.manage2.tkmapper.entity.Menu;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * @author lz
 * @description
 * @date 2019/5/24
 */
@Table(name = "[menu]")
public class MenuDTO extends Menu {
    private List<MenuDTO> childList;
    //叶子节点才有文件
    private List<Map<String, String>> files;

    public List<MenuDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuDTO> childList) {
        this.childList = childList;
    }

    public List<Map<String, String>> getFiles() {
        return files;
    }

    public void setFiles(List<Map<String, String>> files) {
        this.files = files;
    }
}
