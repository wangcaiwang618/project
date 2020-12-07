package com.wl.sm.dao;

import com.wl.sm.entity.Admin;

import java.sql.SQLException;

/**
 * @ClassName AdminDao
 * @Description 管理员接口
 * @Author WL
 * @Date 2020/11/19
 **/
public interface AdminDao {
    /**
     * 根据账号查找管理员
     * @param account 账号入参
     * @return 管理员信息
     * @throws SQLException 该方法会抛出SQL异常
     */
    Admin findAdminByAccount(String account) throws SQLException;
}
