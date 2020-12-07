package com.wl.sm.service;

import com.wl.sm.entity.Department;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author WL
 * @Date 2020/11/19
 **/
public interface DepartmentService {
    /**
     * 查询所有院系
     * @return List<Department>
     */
    List<Department> selectAll();

    /**
     * 删除院系
     * @param department 院系对象
     * @param id 院系id
     * @return 删除结果
     */
    int delDepartment(Department department, Integer id);

    /**
     * 添加院系
     * @param department 入参
     * @return 结果
     */
    int addDepartment(Department department);
}
