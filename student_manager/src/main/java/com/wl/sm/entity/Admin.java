package com.wl.sm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Admin
 * @Description Admin实体类
 * @Author WL
 * @Date 2020/11/19
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer id;
    private String account;
    private String password;
    private String adminName;
}
