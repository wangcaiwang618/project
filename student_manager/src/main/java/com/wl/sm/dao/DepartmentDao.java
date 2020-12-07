package com.wl.sm.dao;

import com.wl.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentDao
 * @Description 院系Dao接口
 * @Author WL
 * @Date 2020/11/19
 **/
public interface DepartmentDao {
    /**
     * 查询所有院系
     * @return List<Department>
     * @throws SQLException 异常
     */
    List<Department> getAll() throws SQLException;

    /**
     * 删除院系
     * @param department 院系
     * @param id 院系id
     * @return 删除结果
     * @throws SQLException 异常
      */
    int deleteDepartment(Department department, Integer id) throws SQLException;

    /**
     * 新增院系
     * @param department 入参
     * @return int
     * @throws SQLException 异常
     */
    int insertDepartment(Department department) throws SQLException;
}
