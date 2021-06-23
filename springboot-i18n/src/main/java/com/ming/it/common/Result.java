package com.ming.it.common;

import lombok.Data;

/**
 * @author 11119638
 * @date 2020/12/7 11:04
 */
/*
 * ?表示不确定的java类型
 * T(Type)表示具体的一个java类型
 * K V(key value)分别代表java键值中的Key和Value
 * E(element)表示Element
 * */
@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public static Result<?> success() {
        Result<?> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    // 第一个<T>申明T为泛型
    public static <T> Result<T> success(T data) {
        final Result<T> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
