package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[room_file]")
public class RoomFile {
    @Column(name = "[file_guid]")
    private String fileGuid;

    @Column(name = "[room_guid]")
    private String roomGuid;

    @Column(name = "[menu_guid]")
    private String menuGuid;

    /**
     * @return file_guid
     */
    public String getFileGuid() {
        return fileGuid;
    }

    /**
     * @param fileGuid
     */
    public void setFileGuid(String fileGuid) {
        this.fileGuid = fileGuid == null ? null : fileGuid.trim();
    }

    /**
     * @return room_guid
     */
    public String getRoomGuid() {
        return roomGuid;
    }

    /**
     * @param roomGuid
     */
    public void setRoomGuid(String roomGuid) {
        this.roomGuid = roomGuid == null ? null : roomGuid.trim();
    }

    /**
     * @return menu_guid
     */
    public String getMenuGuid() {
        return menuGuid;
    }

    /**
     * @param menuGuid
     */
    public void setMenuGuid(String menuGuid) {
        this.menuGuid = menuGuid == null ? null : menuGuid.trim();
    }
}