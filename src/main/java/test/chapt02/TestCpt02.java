package test.chapt02;

import binding.MapperRegistry;
import session.SqlSession;
import session.SqlSessionFactory;
import session.defaults.DefaultSqlSessionFactory;
import test.chapt02.dao.IUserDao;

public class TestCpt02 {
    public static void main(String[] args) {
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("test.chapt02.dao");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String res = userDao.queryUserName("1001");
        System.out.println(res);
    }
}
