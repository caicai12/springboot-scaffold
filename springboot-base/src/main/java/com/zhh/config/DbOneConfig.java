package com.zhh.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description dbOne数据源配置
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/25 10:54
 */
@Configuration
@MapperScan(basePackages = DbOneConfig.DAO_LOCATION, sqlSessionFactoryRef = "dbOneSqlSessionFactory")
public class DbOneConfig {
    static final String DAO_LOCATION = "com.zhh.dao.dbOne.**";

    static final String MAPPER_LOCATION = "classpath*:mapper/dbOne/*.xml";

    @Value("${spring.datasource.dbOne.url}")
    private String url;

    @Value("${spring.datasource.dbOne.username}")
    private String username;

    @Value("${spring.datasource.dbOne.password}")
    private String password;

    @Value("${spring.datasource.dbOne.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.dbOne.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.dbOne.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.dbOne.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.dbOne.maxWait}")
    private long maxWait;

    @Value("${spring.datasource.dbOne.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.dbOne.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${spring.datasource.dbOne.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.dbOne.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.dbOne.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.dbOne.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.dbOne.logSlowSql}")
    private boolean logSlowSql;

    @Value("${spring.datasource.dbOne.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.dbOne.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.dbOne.filters}")
    private String filters;

    @Primary
    @Bean(name="dbOneDataSource")
    public DataSource dbOneDataSource() throws SQLException {
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
    @Primary
    @Bean(name = "dbOneTransactionManager")
    public DataSourceTransactionManager dbOneTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(dbOneDataSource());
    }

    //会话工厂
    @Primary
    @Bean(name = "dbOneSqlSessionFactory")
    public SqlSessionFactory dbOneSqlSessionFactory(@Qualifier("dbOneDataSource") DataSource dbOneDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 设置数据源bean
        sessionFactory.setDataSource(dbOneDataSource);
        // 设置mapper文件路径
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        //返回SqlSessionFactory
        return sessionFactory.getObject();
    }
}
