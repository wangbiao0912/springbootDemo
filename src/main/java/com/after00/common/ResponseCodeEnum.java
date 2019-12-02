package com.after00.common;

/**
 * 杩斿洖娑堟伅浠ｇ爜鏋氫妇
 */
public enum ResponseCodeEnum {
    success(200, "success"), fail(500, "fail"), invalid(5000, "fail");

    public Integer key;
    public String text;

    ResponseCodeEnum(Integer key, String text) {
        this.key = key;
        this.text = text;
    }

    /**
     * 返回执行key枚举
     *
     * @param key
     * @return
     */
    public static ResponseCodeEnum fromKey(String key) {
        ResponseCodeEnum[] dics = ResponseCodeEnum.values();
        for (ResponseCodeEnum d : dics) {
            if (d.key.equals(key)) {
                return d;
            }
        }
        return null;
    }
}
