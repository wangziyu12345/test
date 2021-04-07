package com.wzy.entiy;

import lombok.Data;

/**
 * 作者：王子瑜
 * 时间：2020/9/17 14:23
 * 描述：
 */
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    private static Result result = new Result();

    //静态属性
    private static final Integer SUCCESS_CODE=200;//成功状态码
    private static final Integer ERROR_CODE=500;//失败状态码

    public static Result error(){
        result.setCode(ERROR_CODE);
        result.setMessage("error");
        return result;
    }
}
