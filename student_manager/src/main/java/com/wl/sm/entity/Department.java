package com.wl.sm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.INTERNAL;

/**
 * @ClassName Department
 * @Description 院系实体类
 * @Author WL
 * @Date 2020/11/19
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Integer id;
    private String departmentName;
    private String logo;
    @Override
    public String toString() {
        return departmentName;
    }
    public String getDepartment() {
        return departmentName;
    }
}

