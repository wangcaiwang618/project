package com.wl.sm.factory;

import com.wl.sm.service.AdminService;
import com.wl.sm.service.ClazzService;
import com.wl.sm.service.DepartmentService;
import com.wl.sm.service.impl.AdminServiceImpl;
import com.wl.sm.service.impl.ClazzServiceImpl;
import com.wl.sm.service.impl.DepartmentServiceImpl;

/**
 * @ClassName ServiceFactory
 * @Description Service工厂类
 * @Author WL
 * @Date 2020/11/19
 **/
public class ServiceFactory {
    public static AdminService getAdminServiceInstance() {
        return new AdminServiceImpl();
    }
    public static DepartmentService getDepartmentServiceInstance() {
        return new DepartmentServiceImpl();
    }
    public static ClazzService getClazzServiceInstance() {
        return new ClazzServiceImpl();
    }
}
