package com.monochrome.dynamicdatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author monochrome
 * @date 2023/1/26
 */
@Configuration
public class DataSourceConfig {


    /**
     * @return 数据源
     * @Bean: 向IOC容器中注入一个Bean
     * @ConfigurationProperties: 使得配置文件中以spring.datasource为前缀的属性映射到Bean的属性中
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        // 做一些其他的自定义配置，比如密码加密等......
        return new DruidDataSource();
    }

    /**
     * 注入SqlSessionFactory
     */
    @Bean("mabatisSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new
                PathMatchingResourcePatternResolver()
                .getResources("classpath*:/mapper/**/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new
                org.apache.ibatis.session.Configuration();
        // 自动将数据库中的下划线转换为驼峰格式
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultFetchSize(100);
        configuration.setDefaultStatementTimeout(30);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建动态数据源的SqlSessionFactory，传入的是动态数据源
     * @Primary这个注解很重要，如果项目中存在多个SqlSessionFactory，这个注解一定要加上
     */
    @Primary
    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultFetchSize(100);
        configuration.setDefaultStatementTimeout(30);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 向IOC容器中注入另外一个数据源
     * 全局配置文件中前缀是spring.datasource.his
     */
    @Bean(name = SwitchSource.DEFAULT_NAME)
    @ConfigurationProperties(prefix = "spring.datasource.his")
    public DataSource hisDataSource() {
        return DataSourceBuilder.create().build();
    }
    /**
     * 重写事务管理器，管理动态数据源
     */
    @Primary
    @Bean(value = "transactionManager2")
    public PlatformTransactionManager annotationDrivenTransactionManager(DynamicDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
