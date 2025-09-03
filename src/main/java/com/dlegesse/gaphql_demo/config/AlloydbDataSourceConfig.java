package com.dlegesse.gaphql_demo.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

@Configuration
@ConfigurationProperties("spring.alloydb-secondary-datasource")
@Data
public class AlloydbDataSourceConfig {

    private String url;
    private String username;
    private String password;
    private Map<String, String> properties;
    private Integer minimumIdle;
    private Integer maximumPoolSize;
    private String driverClassName;

    @Bean
    public DataSource alloyDBdataSource() throws Exception {
        System.out.println("Creating Alloydb Datasource Config");
        System.out.println("URL: " + url );
        System.out.println("User: " + username );
        System.out.println("Password: " + password );
        System.out.println("minimumIdle: " + minimumIdle );
        System.out.println("maximumPoolSize: " + maximumPoolSize );
        System.out.println("driverClassName: " + driverClassName );


        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);



        if (minimumIdle != null) config.setMinimumIdle(minimumIdle);
        if (maximumPoolSize != null) config.setMaximumPoolSize(maximumPoolSize);
        if (driverClassName != null) config.setDriverClassName(driverClassName);

        Properties dsProperties = new Properties();

        if (properties != null) {
            properties.forEach(dsProperties::setProperty);
            System.out.println("properties: " + dsProperties );
        }

        config.setDataSourceProperties(dsProperties);

        HikariDataSource ds = new HikariDataSource(config);

        return ds;

    }
}
