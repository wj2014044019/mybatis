package test.chapt01;

import binding.MapperProxyFactory;

import java.util.HashMap;
import java.util.Map;

public class TestMapperProxyFactory {

    public static void main(String[] args) {
        MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String,String> sqlSession = new HashMap<>();
        sqlSession.put("test.chapt01.IUserDao", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("test.chapt01.IUserDao", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        IUserDao userDao = proxyFactory.newInstance(sqlSession);
        String res = userDao.queryUserName("1001");
        System.out.println(res);
    }
}
