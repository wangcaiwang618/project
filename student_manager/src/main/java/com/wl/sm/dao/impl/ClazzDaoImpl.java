package com.wl.sm.dao.impl;

import com.wl.sm.dao.ClazzDao;
import com.wl.sm.entity.Clazz;
import com.wl.sm.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName ClazzDaoImpl
 * @Description ClazzDao实现类
 * @Author WL
 * @Date 2020/11/20
 **/
public class ClazzDaoImpl implements ClazzDao {

    @Override
    public List<Clazz> selectByDepartmentId(int department) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_class WHERE department_id = ? ORDER BY id desc";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, department);
        ResultSet rs = pstmt.executeQuery();
        List<Clazz> clazzList = new ArrayList<>();
        while (rs.next()) {
            Clazz clazz = new Clazz();
            clazz.setId((rs.getInt("id")));
            clazz.setDepartmentId(rs.getInt("department_id"));
            clazz.setClassName(rs.getString("Class_name"));
            clazzList.add(clazz);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return clazzList;
    }
}
