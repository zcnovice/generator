package com.zc.maker.meta.enums;

public enum FileTypeEnum {

    /* 一个中文描述 */
    DIR("目录", "dir"),
    FILE("文件", "file");

    /* 一般用final来修饰，因为一般情况下不会修改 (类加载时就定了)*/
    private final String text;

    private final String value;

    FileTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
