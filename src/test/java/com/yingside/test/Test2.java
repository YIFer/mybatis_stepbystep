package com.yingside.test;

import com.yingside.bean.Dept;
import com.yingside.bean.Employee;
import com.yingside.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Date;

public class Test2 {
    private static Logger log = Logger.getLogger(Test.class);

    //测试一级缓存1
    @org.junit.Test
    public void testLocalCache1(){
        String resource = "mybatis-configuration.xml";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        System.out.println("--------------------------------------根据主键id=1查询用户信息 开始--------------------------------------");
        String stmt = "com.yingside.mapper.UserMapper.getUser";
        User user1 = sqlSession.selectOne(stmt,1);
        log.info(user1);
        //sqlSession.close();
        System.out.println("--------------------------------------根据主键id=1查询用户信息 结束--------------------------------------");
        //手动清空一级缓存
        //sqlSession.clearCache();
        //提交事务,实际也会清空一级缓存
        sqlSession.commit();
        //相同的id再次查询
        System.out.println("--------------------------------------根据主键id=1查询用户信息 开始--------------------------------------");
        //打开一个新的sqlSession,也就会有一个新的一级缓存了,因为一级缓存是依附于SqlSession对象的
        //sqlSession = factory.openSession();
        User user2 = sqlSession.selectOne(stmt,1);
        log.info(user2);
        System.out.println("--------------------------------------根据主键id=1查询用户信息 结束--------------------------------------");
        sqlSession.close();
    }

    //测试二级缓存
    @org.junit.Test
    public void testLocalCache2(){
        String resource = "mybatis-configuration.xml";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        //根据员工主键id查询
        System.out.println("--------------------------------------根据员工主键id=1查询员工信息并级联部门信息 开始--------------------------------------");
        String stmt = "com.yingside.mapper.EmployeeMapper.getEmployeeById";
        Employee employee1 = sqlSession.selectOne(stmt,1);
        log.info(employee1);
        System.out.println("--------------------------------------根据员工主键id=1查询员工信息并级联部门信息 结束--------------------------------------");
        //部门修改
        System.out.println("--------------------------------------根据部门id=2更新部门信息 开始--------------------------------------");
        String stmt2 = "com.yingside.mapper.DeptMapper.updateDeptByIdSelective";
        Dept dept = new Dept();
        dept.setDeptId(2);
        dept.setDeptName("人事部");
        dept.setDeptCreateDate(new Date());
        dept.setDeptInfo("员工薪资,员工激励,员工招聘,团队建设");
        int n = sqlSession.update(stmt2,dept);
        sqlSession.commit();
        log.info(n);
        System.out.println("--------------------------------------根据部门id=2更新部门信息 结束--------------------------------------");
        //相同的id再次查询
        System.out.println("--------------------------------------根据员工主键id=1查询员工信息并级联部门信息 开始--------------------------------------");
        Employee employee2 = sqlSession.selectOne(stmt,1);
        log.info(employee2);
        System.out.println("--------------------------------------根据员工主键id=1查询员工信息并级联部门信息 结束--------------------------------------");
    }
}
