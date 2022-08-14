package binding;

import cn.hutool.core.lang.ClassScanner;
import session.SqlSession;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 提供包路径的扫描和映射器代理类注册机服务，完成接口对象的代理类注册处理
 */
public class MapperRegistry {
    private final Map<Class<?>,MapperProxyFactory<?>> knowMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knowMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to MapperRegistry");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("error getting mapper instance. Cause" + e,e);
        }
    }

    public <T> void addMapper (Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            knowMappers.put(type,new MapperProxyFactory<>(type));

        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knowMappers.containsKey(type);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}
