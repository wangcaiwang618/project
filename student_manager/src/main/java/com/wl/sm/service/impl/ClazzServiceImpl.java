package com.wl.sm.service.impl;

import com.wl.sm.dao.ClazzDao;
import com.wl.sm.entity.Clazz;
import com.wl.sm.factory.DaoFactory;
import com.wl.sm.factory.ServiceFactory;
import com.wl.sm.service.ClazzService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzServiceImpl
 * @Description TODO
 * @Author WL
 * @Date 2020/11/20
 **/
public class ClazzServiceImpl implements ClazzService {
    private final ClazzDao clazzService = DaoFactory.getClazzDaoInstance();
    @Override
    public List<Clazz> getClazzByDepId(int depId) {
        List<Clazz> classList = null;
        try {
            classList = clazzService.selectByDepartmentId(depId);
        } catch (SQLException e) {
            System.err.println("查询班级信息出现异常！");
        }
        return classList;
    }
}
