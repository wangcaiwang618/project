package com.wl.sm.dao;

import com.wl.sm.entity.Clazz;
import com.wl.sm.factory.DaoFactory;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzDaoTest
 * @Description TODO
 * @Author WL
 * @Date 2020/11/20
 **/
public class ClazzDaoTest extends TestCase {
    private final ClazzDao clazzDao = DaoFactory.getClazzDaoInstance();

    @Test
    public void testSelectByDepartmentId() {
        List<Clazz> clazzList = null;
        try {
            clazzList = clazzDao.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(clazzList);
    }
}