package com.wzy.entiy;

public enum ContantEnum {
    OK(200,"ok"),
    FILE(-1,"失败");

    private int code;
    private String value;

    ContantEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
