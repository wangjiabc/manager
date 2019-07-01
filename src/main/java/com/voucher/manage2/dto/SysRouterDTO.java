package com.voucher.manage2.dto;

import com.voucher.manage2.tkmapper.entity.SysRouter;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lz
 * @description
 * @date 2019/6/28
 */
@Table(name = "[sys_router]")
public class SysRouterDTO extends SysRouter {
    private Map<String, String> meta = new HashMap<>(4);
    private List<SysRouterDTO> children;

    private boolean seleced = false;

    public boolean isSeleced() {
        return seleced;
    }

    public void setSeleced(boolean seleced) {
        this.seleced = seleced;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    public List<SysRouterDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SysRouterDTO> children) {
        this.children = children;
    }
}
