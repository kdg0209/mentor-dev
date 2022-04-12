package com.intw.mentorapi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.intw.mentorapi.mapper")
@EnableTransactionManagement
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception {
        // mybatis config xml 설정 세팅
        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/config/config.xml");

        // mybatis xml 설정 세팅
        final SqlSessionFactoryBean sessionFactory =new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.intw.mentorapi.dao");
        sessionFactory.setConfigLocation(myBatisConfig);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)throws Exception {
        final SqlSessionTemplate sqlSessionTemplate =new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
