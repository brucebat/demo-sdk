package com.brucebat.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2023/3/13 16:08
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;

    /**
     * 实际数据
     */
    private T data;

    private Result(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 创建失败结果
     *
     * @param code    错误码
     * @param message 错误信息
     * @return 失败结果
     */
    public static Result<?> fail(String code, String message) {
        return new Result<>(false, code, message, null);
    }

    /**
     * 创建成功结果
     *
     * @param code 成功code
     * @param data 对应数据
     * @param <T>  泛化类型
     * @return 处理完成结果
     */
    public static <T> Result<T> success(String code, T data) {
        return new Result<>(true, code, null, data);
    }


}
