package com.wl.sm.factory;

import com.wl.sm.dao.AdminDao;
import com.wl.sm.dao.ClazzDao;
import com.wl.sm.dao.DepartmentDao;
import com.wl.sm.dao.impl.AdminDaoImpl;
import com.wl.sm.dao.impl.ClazzDaoImpl;
import com.wl.sm.dao.impl.DepartmentDaoImpl;
import com.wl.sm.entity.Admin;

/**
 * @ClassName DaoFactory
 * @Description 工厂类
 * @Author WL
 * @Date 2020/11/19
 **/
public class DaoFactory {
    /**
     * 获得Admin实例
     * @return AdminDao实例
     */
    public static AdminDao getAdminDaoInstance() {
        return new AdminDaoImpl();
    }

    /**
     * 获得DepartmentDao实例
     * @return AdminDao实例
     */
    public static DepartmentDao getDepartmentDaoInstance() {
        return new DepartmentDaoImpl();
    }

    /**
     * 获得ClazzDao实例
     * @return ClazzDao实例
     */
    public static ClazzDao getClazzDaoInstance() {
        return new ClazzDaoImpl();
    }
}
