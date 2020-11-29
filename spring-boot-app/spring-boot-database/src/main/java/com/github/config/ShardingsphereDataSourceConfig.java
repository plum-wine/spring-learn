package com.github.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.net.URL;

@Configuration
public class ShardingsphereDataSourceConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        URL resource = ShardingsphereDataSourceConfig.class.getResource("/shardingsphere-jdbc.yml");
        File configFile = new File(resource.getFile());
        return YamlShardingSphereDataSourceFactory.createDataSource(configFile);
    }

}
