package com.wl.sm.service;

import com.wl.sm.entity.Clazz;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzService
 * @Description TODO
 * @Author WL
 * @Date 2020/11/20
 **/
public interface ClazzService {
    /**
     * 根据院系Id查询班级
     * @param depId 院系Id
     * @return 班级集合
     */
    List<Clazz> getClazzByDepId(int depId);
}
