package com.wl.sm.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResultEntity
 * @Description 返回结果实体类
 * @Author WL
 * @Date 2020/11/19
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {
    private int code;
    private String message;
    private Object data;
}
