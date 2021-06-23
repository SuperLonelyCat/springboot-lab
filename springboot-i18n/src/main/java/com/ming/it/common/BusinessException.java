package com.ming.it.common;

/**
 * @author 11119638
 * @date 2021/4/19 16:33
 */
public class BusinessException extends RuntimeException {

    private final Integer code;

    private final String message;

    public BusinessException(String message) {
        this.code = ResultCode.FAILURE.getCode();
        this.message = message;
    }

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(ResultCode resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
