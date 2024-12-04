package com.thiru.db2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.thiru.db2.repository", // Repository location for db2
    entityManagerFactoryRef = "db2EntityManagerFactory",
    transactionManagerRef = "db2TransactionManager"
)
@EntityScan(basePackages = "com.thiru.db2.entity") // Entity location for db2
public class DB2Config {

    @Bean(name = "db2DataSource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb")             // H2 in-memory database
                .username("thiru")                     // Replace with your H2 username
                .password("thiru")                     // Replace with your H2 password
                .driverClassName("org.h2.Driver")      // H2 Driver
                .build();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db2DataSource") DataSource db2DataSource) {
        // Hibernate properties
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update"); // Automatically update schema
        properties.put("hibernate.show_sql", "true"); // Show SQL queries

        return builder
                .dataSource(db2DataSource)
                .packages("com.thiru.db2.entity") // Entity package
                .persistenceUnit("db2")
                .properties(properties) // Apply Hibernate properties
                .build();
    }

    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(
            @Qualifier("db2EntityManagerFactory") EntityManagerFactory db2EntityManagerFactory) {
        return new JpaTransactionManager(db2EntityManagerFactory);
    }
}
