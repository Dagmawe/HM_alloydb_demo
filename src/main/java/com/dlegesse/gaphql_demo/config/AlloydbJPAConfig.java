package com.dlegesse.gaphql_demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.dlegesse.gaphql_demo.Repo.alloydb", entityManagerFactoryRef = "alloyDBlEntityMangerFactoryBean")
public class AlloydbJPAConfig {

    @Bean(name = "alloyDBlEntityMangerFactoryBean")
    LocalContainerEntityManagerFactoryBean alloyDBlEntityMangerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("alloyDBdataSource") DataSource dataSource) {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.format_sql", "true");
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(jpaProperties)
                .packages("com.dlegesse.gaphql_demo.Entity.alloydb")
                .persistenceUnit("alloydb")
                .build();
    }

    @Bean(name = "alloyDbTransactionManager")
    public PlatformTransactionManager alloyDbTransactionManager(
            @Qualifier("alloyDBlEntityMangerFactoryBean") EntityManagerFactory alloyDbEntityManagerFactory) {
        return new JpaTransactionManager(alloyDbEntityManagerFactory);
    }
}




