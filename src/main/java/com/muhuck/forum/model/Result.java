package com.muhuck.forum.model;
import lombok.Data;


/**
 * 返回的结果集封装
 *  @param <T> 结果数据类型
 */
@Data
public class Result<T> {
    private Integer status;// 状态码
    private String message;
    private boolean success;
    private T data;
    /**
     * 创建并返回成功结果，无数据
     *
     * @param message 消息
     * @return 成功结果
     */
    public static <T> Result<T> resultSuccess( String message ) {
        Result<T> result = new Result<>();
        result.setStatus(200);
        result.setSuccess(true);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
    /**
     * 创建并返回成功结果，包含数据
     *
     * @param message 消息
     * @param data    数据体
     * @return 成功结果
     */
    public static <T> Result<T> resultSuccess(String message, T data) {
        Result<T> result = new Result<>();
        result.setStatus(200);
        result.setSuccess(true);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    /**
     * 创建并返回失败结果
     *
     * @param message 消息
     * @return 失败结果
     */
    public static <T> Result<T> resultFailed(String message) {
        Result<T> result = new Result<>();
        result.setStatus(500);
        result.setSuccess(false);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
