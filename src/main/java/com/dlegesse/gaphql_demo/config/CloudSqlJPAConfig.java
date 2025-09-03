package com.dlegesse.gaphql_demo.config;



import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.dlegesse.gaphql_demo.Repo.cloudsql", entityManagerFactoryRef = "cloudSqlEntityMangerFactoryBean", transactionManagerRef = "cloudSqlTransactionManager")
public class CloudSqlJPAConfig {

    @Primary
    @Bean(name = "cloudSqlEntityMangerFactoryBean")
    LocalContainerEntityManagerFactoryBean cloudSqlEntityMangerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("cloudSqldataSource") DataSource dataSource) {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.format_sql", "true");
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(jpaProperties)
                .packages("com.dlegesse.gaphql_demo.Entity.cloudsql")
                .persistenceUnit("cloudsql")
                .build();
    }


    @Primary // Mark this as the primary transaction manager
    @Bean(name = {"cloudSqlTransactionManager", "transactionManager"})
    public PlatformTransactionManager cloudSqlTransactionManager(
            @Qualifier("cloudSqlEntityMangerFactoryBean") EntityManagerFactory cloudSqlEntityManagerFactory) {
        return new JpaTransactionManager(cloudSqlEntityManagerFactory);
    }


}

