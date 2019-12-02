package com.after00.common;

import java.io.Serializable;


public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应代码
     */
    private Integer code;
    /**
     * 响应数据
     */
    private Object data;

    private String messages;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public BaseResponse() {
        this.code = ResponseCodeEnum.success.key;
    }

    public BaseResponse(int code, String messages, Object data) {
        this.code = code;
        this.messages = messages;
        this.data = data;
    }

    public boolean isSuccess() {
        return ResponseCodeEnum.success.key.equals(code);
    }

    /**
     * 请求参数验证失败响应
     *
     * @return
     */
    public static BaseResponse getParamsFailedResponse(String msg) {
        return new BaseResponse(ResponseCodeEnum.fail.key, msg, null);
    }

    /**
     * 成功相应
     *
     * @param data
     * @return
     */
    public static BaseResponse getSuccessResponse(Object data, String msg) {
        return new BaseResponse(ResponseCodeEnum.success.key, msg, data);
    }

    /**
     * 失败响应
     *
     * @param data
     * @return
     */
    public static BaseResponse getFailResponse(int code, Object data, String msg) {
        return new BaseResponse(code == 0 ? ResponseCodeEnum.fail.key : code, msg, data);
    }
}
