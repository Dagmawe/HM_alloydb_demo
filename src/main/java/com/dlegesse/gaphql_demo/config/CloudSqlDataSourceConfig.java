package com.dlegesse.gaphql_demo.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@ConfigurationProperties("spring.datasource")
@Data
public class CloudSqlDataSourceConfig {

    private String url;
    private String username;
    private String password;
    private Map<String, String> properties;
    private Integer minimumIdle;
    private Integer maximumPoolSize;
    private String driverClass;

    @Primary
    @Bean(name = "cloudSqldataSource")
    public DataSource cloudSqldataSource() throws Exception {
        System.out.println("Creating Cloudsql Datasource Config");
        System.out.println("URL: " + url );
        System.out.println("User: " + username );
        System.out.println("Password: " + password );
        System.out.println("minimumIdle: " + minimumIdle );
        System.out.println("maximumPoolSize: " + maximumPoolSize );


        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMinimumIdle(minimumIdle);
        config.setMaximumPoolSize(maximumPoolSize);

        if (properties != null) {
            properties.forEach(config::addDataSourceProperty);
        }

        HikariDataSource ds = new HikariDataSource(config);

        return ds;

    }
}
