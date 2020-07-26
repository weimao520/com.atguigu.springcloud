package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WeiMao
 * @create 2020-07-23 22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {


    private Integer code;
    private String message;

    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }

}
