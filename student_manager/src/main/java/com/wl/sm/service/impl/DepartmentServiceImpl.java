package com.wl.sm.service.impl;

import com.wl.sm.dao.DepartmentDao;
import com.wl.sm.entity.Department;
import com.wl.sm.factory.DaoFactory;
import com.wl.sm.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentServiceImpl
 * @Description DepartmentService实现类
 * @Author WL
 * @Date 2020/11/19
 **/
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao = DaoFactory.getDepartmentDaoInstance();

    @Override
    public List<Department> selectAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDao.getAll();
        } catch (SQLException e) {
            System.err.println("查询院系信息出现异常！");
        }
        return departmentList;
    }

    @Override
    public int delDepartment(Department department, Integer id) {
        int result = 0;
        try{
            result = departmentDao.deleteDepartment(department, id);
        } catch (SQLException e) {
            System.err.println("删除院系出错！");
        }
        return result;

    }

    @Override
    public int addDepartment(Department department) {
        int n = 0;
        try {
             n = departmentDao.insertDepartment(department);
        } catch (SQLException e){
            System.err.println("添加院系失败！");
        }
        return n;
    }
}
