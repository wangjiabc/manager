package com.voucher.manage2.constant;

public enum FileConstant {


    OTHER(0, "其他"),
    IMAGE(1, "图片"),
    EXCEL(2, "excel"),
    WORD(3, "word"),
    PDF(4, "pdf");

    public Integer type;
    public String name;

    FileConstant(Integer type, String name) {
        this.type = type;
        this.name = name;
    }


}
