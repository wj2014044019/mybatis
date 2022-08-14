package session.defaults;

import binding.MapperRegistry;
import session.SqlSession;
import session.SqlSessionFactory;

public class DefaultSqlSessionFactory  implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
