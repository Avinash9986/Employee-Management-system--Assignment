package com.example.Employee.Management.System.assignment.db.postgres.Config;


import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean ",
        basePackages = {"com.example.Employee.Management.System.assignment.db.postgres.Repository"},
        transactionManagerRef = "transactionManager"
)
public class postgresConfig {

    @Autowired
    private Environment environment ;
    //datasource

    @Bean(name = "secondDataSource")
    @Primary
    public DataSource dataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource() ;
        dataSource.setUrl(environment.getProperties().getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperties().getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperties().getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(environment.getProperties().getProperty("spring.datasource.driver-class-name"));
        return  dataSource ;
    }

    //entitymanager facory
    @Bean(name = "entityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean() ;
        bean.setDataSource(dataSource());

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter() ;
        bean.setJpaVendorAdapter(adapter);

        Map<String, String> props = new HashMap<>() ;
        props.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect") ;
        props.put("hibernate.show_sql","true") ;
        props.put("hibernate.hbm2ddl.auto","update") ;
        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan("com.example.Employee.Management.System.assignment.db.postgres.models");
        return  bean ;
    }


    //platfofrmtransacion manager
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(){

        JpaTransactionManager manager = new JpaTransactionManager() ;
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return manager ;
    }
}
