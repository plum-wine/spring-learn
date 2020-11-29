package com.github.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.File;
import java.net.URL;

@Configuration
@MapperScan(
        basePackages = "com.github.dao.order",
        sqlSessionFactoryRef = "shardingDataSourceSqlSessionFactory",
        sqlSessionTemplateRef = "shardingSqlSessionTemplate"
)
public class ShardingDataSourceConfig {

    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Value("${mybatis.type-handlers-package}")
    private String typeHandlersPackage;

    @Value("${mybatis.config-location}")
    private String configLocation;

    @Value("${mybatis.sharding.mapperLocations}")
    private String mapperLocation;

    @Bean("shardingSqlSessionTemplate")
    public SqlSessionTemplate shardingSqlSessionTemplate(@Qualifier("shardingDataSourceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("shardingDataSourceSqlSessionFactory")
    public SqlSessionFactory shardingDataSourceSqlSessionFactory(@Qualifier("shardingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(typeAliasesPackage);
        bean.setTypeHandlersPackage(typeHandlersPackage);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        return bean.getObject();
    }

    @Bean("shardingDataSource")
    public DataSource shardingDataSource() throws Exception {
        URL resource = ShardingDataSourceConfig.class.getResource("/shardingsphere-jdbc.yml");
        File configFile = new File(resource.getFile());
        return YamlShardingSphereDataSourceFactory.createDataSource(configFile);
    }

}
