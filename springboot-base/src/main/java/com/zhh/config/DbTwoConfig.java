package com.zhh.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description dbTwo数据源配置
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/25 10:54
 */
@Configuration
@MapperScan(basePackages = DbTwoConfig.DAO_LOCATION, sqlSessionFactoryRef = "dbTwoSqlSessionFactory")
public class DbTwoConfig {
    static final String DAO_LOCATION = "com.zhh.dao.dbTwo.**";

    static final String MAPPER_LOCATION = "classpath*:mapper/dbTwo/*.xml";

    @Value("${spring.datasource.dbTwo.url}")
    private String url;

    @Value("${spring.datasource.dbTwo.username}")
    private String username;

    @Value("${spring.datasource.dbTwo.password}")
    private String password;

    @Value("${spring.datasource.dbTwo.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.dbTwo.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.dbTwo.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.dbTwo.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.dbTwo.maxWait}")
    private long maxWait;

    @Value("${spring.datasource.dbTwo.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.dbTwo.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${spring.datasource.dbTwo.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.dbTwo.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.dbTwo.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.dbTwo.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.dbTwo.logSlowSql}")
    private boolean logSlowSql;

    @Value("${spring.datasource.dbTwo.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.dbTwo.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.dbTwo.filters}")
    private String filters;

    @Bean(name="dbTwoDataSource")
    public DataSource dbTwoDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        // 监控统计拦截的filters
        druid.setFilters(filters);
        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);
        //初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);
        //最大连接池数量
        druid.setMaxActive(maxActive);
        //最小连接池数量
        druid.setMinIdle(minIdle);
        //获取连接时最大等待时间，单位毫秒。
        druid.setMaxWait(maxWait);
        //间隔多久进行一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接在池中最小生存的时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测连接是否有效的sql
        druid.setValidationQuery(validationQuery);
        //建议配置为true，不影响性能，并且保证安全性。
        druid.setTestWhileIdle(testWhileIdle);
        //申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);
        //是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);
        // 打开PSCache时，指定每个连接上PSCache的大小
        druid.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return druid;
    }

    //事务管理
    @Bean(name = "dbTwoTransactionManager")
    public DataSourceTransactionManager dbTwoTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(dbTwoDataSource());
    }

    //会话工厂
    @Bean(name = "dbTwoSqlSessionFactory")
    public SqlSessionFactory dbTwoSqlSessionFactory(@Qualifier("dbTwoDataSource") DataSource dbTwoDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 设置数据源bean
        sessionFactory.setDataSource(dbTwoDataSource);
        // 设置mapper文件路径
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        //返回SqlSessionFactory
        return sessionFactory.getObject();
    }
}
