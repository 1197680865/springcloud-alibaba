package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  通用返回类型
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// 泛型 T，传入payment就返回payment
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
