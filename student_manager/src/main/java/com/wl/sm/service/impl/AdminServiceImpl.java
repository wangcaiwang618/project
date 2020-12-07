package com.wl.sm.service.impl;

import com.wl.sm.dao.AdminDao;
import com.wl.sm.entity.Admin;
import com.wl.sm.factory.DaoFactory;
import com.wl.sm.service.AdminService;
import com.wl.sm.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author WL
 * @Date 2020/11/19
 **/
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = DaoFactory.getAdminDaoInstance();
    @Override
    public ResultEntity adminLogin(String account, String password) {
        ResultEntity resultEntity = null;
        Admin admin = null;
        try{
            //格局账号查找管理员信息。抛出SQL异常
            admin = adminDao.findAdminByAccount(account);
        } catch (SQLException e) {
            System.err.println("根据账号查找管理员信息出现错误！");
        }
        //根据账号查找记录
        if (admin != null) {
            //比较密码，此时需要将客户传过来的密码进行HD5加密后才能比较
            if (DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                resultEntity = ResultEntity.builder().code(0).message("登陆成功！").data(admin).build();
            } else {
                //账号不存在
                resultEntity = ResultEntity.builder().code(1).message("密码错误！").build();
            }
        }
        return resultEntity;
    }
}
