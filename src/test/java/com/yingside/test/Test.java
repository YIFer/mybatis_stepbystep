package com.yingside.test;

import com.yingside.bean.Dept;
import com.yingside.bean.Employee;
import com.yingside.bean.QueryBean;
import com.yingside.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    SqlSession sqlSession = null;
    private static Logger log = Logger.getLogger(Test.class);
    @Before
    public void init(){
        //mybatis全局配置文件
        String resource = "mybatis-configuration.xml";
        //加载全局配置文件
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
        //创建SqlSession工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //通过工厂生成sqlSession
        sqlSession = factory.openSession();

    }

    @org.junit.Test
    public void testGetUser(){
        /*这个字符串由 userMapper.xml 文件中 两个部分构成
        <mapper namespace="com.yingside.mapper.UserMapper"> 的 namespace 的值
        <select id="getUser" > id 值
        其实就相当于之前Dao层中xxxDaoImpl.getUser()方法
        */
        String stmt = "com.yingside.mapper.UserMapper.getUser";
        //传入字符串以及参数id
        User user = sqlSession.selectOne(stmt,1);
        log.info(user);
        //关闭sqlSession
        sqlSession.close();
    }

    @org.junit.Test
    public void testGetUserByName(){
        String stmt = "com.yingside.mapper.UserMapper.getUserByName";
        //传入字符串以及参数id
        List<User> users = sqlSession.selectList(stmt,"张三丰");
        log.info(users);
        //关闭sqlSession
        sqlSession.close();
    }

    @org.junit.Test
    public void testGetUserBySelective(){
        String stmt = "com.yingside.mapper.UserMapper.getUserBySelective";
        User user = new User();
        //根据电话,姓名和时间条件一起查询
        user.setUserTel("1388");
        user.setUsername("张");
        user.setRegistrationTime("2018-12-15");
        List<User> users = sqlSession.selectList(stmt,user);
        log.info(users);
        sqlSession.close();
    }

    @org.junit.Test
    public void testGetUserBySelective2(){
        String stmt = "com.yingside.mapper.UserMapper.getUserBySelective2";
        User user = new User();
        //user对象中给电话,姓名都赋值
        user.setUserTel("1388");
        user.setUsername("张");
        //user.setRegistrationTime("2018-12-15");
        List<User> users = sqlSession.selectList(stmt,user);
        log.info(users);
        sqlSession.close();
    }

    @org.junit.Test
    public void testGetAllUsers(){
        String stmt = "com.yingside.mapper.UserMapper.getAll";
        List<User> users = sqlSession.selectList(stmt);
        log.info(users);
        //关闭sqlSession
        sqlSession.close();
    }

    @org.junit.Test
    public void testGetUserByQueryIds(){
        String stmt = "com.yingside.mapper.UserMapper.getUserByQueryIds";
        //封装QueryBean并传入id
        QueryBean queryBean = new QueryBean();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);ids.add(4);ids.add(5);ids.add(8);
        queryBean.setIds(ids);
        //将查询类对象queryBean传入
        List<User> users = sqlSession.selectList(stmt, queryBean);
        log.info(users);
        sqlSession.close();
    }

    @org.junit.Test
    public void testInsertUser(){
        String stmt = "com.yingside.mapper.UserMapper.insertUser";
        User user = new User();
        user.setUsername("任我行");
        user.setPassword("abcdefg");
        user.setUserTel("13880000011");
        //user.setRegistrationTime("2018-12-31");
        int n = sqlSession.insert(stmt,user);
        //提交新增的数据
        sqlSession.commit();
        //从user对象中直接获取主键id值
        log.info("受影响的行数:" + n + ",新增数据的主键id是:" + user.getId());
        sqlSession.close();
    }

    @org.junit.Test
    public void testInsertUserSelective(){
        String stmt = "com.yingside.mapper.UserMapper.insertUserSelective";
        User user = new User();
        user.setUsername("任盈盈");
        user.setPassword("abcdefg");
        user.setUserTel("13880000012");
        //user.setRegistrationTime("2018-12-31");
        int n = sqlSession.insert(stmt,user);
        //提交新增的数据
        sqlSession.commit();
        //从user对象中直接获取主键id值
        log.info("受影响的行数:" + n + ",新增数据的主键id是:" + user.getId());
        sqlSession.close();
    }

    @org.junit.Test
    public void testInsertUserSelective2(){
        //下面执行的代码和InsertUserSelective一模一样,只是使用了SQL代码片段
        String stmt = "com.yingside.mapper.UserMapper.insertUserSelective2";
        User user = new User();
        user.setUsername("张敏");
        user.setPassword("abcdefg");
        user.setUserTel("13880000013");
        user.setRegistrationTime("2019-01-03");
        int n = sqlSession.insert(stmt,user);
        //提交新增的数据
        sqlSession.commit();
        //从user对象中直接获取主键id值
        log.info("受影响的行数:" + n + ",新增数据的主键id是:" + user.getId());
        sqlSession.close();
    }

    @org.junit.Test
    public void testDeleteUser(){
        String stmt = "com.yingside.mapper.UserMapper.deleteUserById";
        int id = 13;
        int n = sqlSession.delete(stmt,id);
        //提交新增的数据
        sqlSession.commit();
        log.info(n);
        sqlSession.close();
    }

    @org.junit.Test
    public void testUpdateUser(){
        String stmt = "com.yingside.mapper.UserMapper.updateUserById";
        User user = new User();
        user.setId(8);
        user.setUsername("黄药师");
        //user.setPassword("abcdefg");
        //user.setUserTel("13880000008");
        //user.setRegistrationTime("2018-12-30");
        int n = sqlSession.insert(stmt,user);
        sqlSession.commit();
        sqlSession.close();
    }

    @org.junit.Test
    public void testUpdateUserByIdSelective(){
        String stmt = "com.yingside.mapper.UserMapper.updateUserByIdSelective";
        User user = new User();
        user.setId(8);
        user.setUsername("黄药师");
        user.setPassword("abcdefg");
        user.setUserTel("13880000008");
        user.setRegistrationTime("2018-12-30");
        int n = sqlSession.update(stmt,user);
        sqlSession.commit();
        sqlSession.close();
    }


    //通过主键id获取Employee对象信息
    @org.junit.Test
    public void testGetEmployeeById(){
        String stmt = "com.yingside.mapper.EmployeeMapper.getEmployeeById";
        Employee employee = sqlSession.selectOne(stmt,1);
        log.info(employee);
        sqlSession.close();
    }

    //查询部门所有信息
    @org.junit.Test
    public void testGetDeptList(){
        String stmt = "com.yingside.mapper.DeptMapper.getDeptList";
        List<Dept> depts = sqlSession.selectList(stmt);
        for (Dept dept : depts){
            System.out.println(dept.getDeptName());
        }
        //获取集合中下标为0的部门下所有的员工
        List<Employee> emps1 = depts.get(0).getEmployeeList();
        sqlSession.close();
    }

    //测试部门更新
    @org.junit.Test
    public void testUpdateDeptByIdSelective(){
        String stmt = "com.yingside.mapper.DeptMapper.updateDeptByIdSelective";
        Dept dept = new Dept();
        dept.setDeptId(3);
        dept.setDeptName("测试部");
        dept.setDeptCreateDate(new Date());
        dept.setDeptInfo("测试软件,白盒测试,黑盒测试");
        int n = sqlSession.update(stmt,dept);
        sqlSession.commit();
        log.info(n);
    }

}
