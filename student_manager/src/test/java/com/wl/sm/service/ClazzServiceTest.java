package com.wl.sm.service;

import com.wl.sm.entity.Clazz;
import com.wl.sm.factory.ServiceFactory;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName ClazzServiceTest
 * @Description TODO
 * @Author WL
 * @Date 2020/11/20
 **/
public class ClazzServiceTest extends TestCase {

    @Test
    public void testGetClazzByDepId() {
        List<Clazz> list = ServiceFactory.getClazzServiceInstance().getClazzByDepId(5);
        list.forEach(System.out::println);
    }
    @Test
    public void testTestGetClazzByDepId() {
        List<Clazz> list = ServiceFactory.getClazzServiceInstance().getClazzByDepId(5);
        list.forEach(System.out::println);
    }
}