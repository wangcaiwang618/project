package com.wl.sm.dao;

import com.wl.sm.entity.Clazz;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzDao
 * @Description 班级dao接口
 * @Author WL
 * @Date 2020/11/20
 **/
public interface ClazzDao {
    /**
     * 根据院系id查询班级
     * @param departmentId
     * @return List<Clazz> 院系班级集合
     * @throws SQLException 异常
     */
    List<Clazz> selectByDepartmentId (int departmentId) throws SQLException;
}
