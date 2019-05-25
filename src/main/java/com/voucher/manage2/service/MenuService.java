package com.voucher.manage2.service;

import java.util.Map;

public interface MenuService {
    Object selectMenu(String parentGuid, String roomGuid);

    Integer insertMenu(Map<String, Object> jsonMap);

    Integer delMenu(Map<String, Object> jsonMap);

    Integer updateMenu(Map<String, Object> jsonMap);
}
