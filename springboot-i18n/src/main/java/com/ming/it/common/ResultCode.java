package com.ming.it.common;

/**
 * @author 11119638
 * @date 2021/4/19 17:00
 */
public enum ResultCode {

    /** 请求成功 */
    SUCCESS(200, "Request Success"),

    /** 请求失败 */
    FAILURE(400, "Request Failure"),

    /** 系统内部错误 */
    EXCEPTION(500, "Internal Server Error"),

    /** 对象复制异常 */
    OBJECT_COPY_EXCEPTION(801, "Object Copy Exception"),

    /** 对象不存在 */
    OBJECT_NOT_EXIST(802, "Object Not Exist");

    private final int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultCode setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}