package com.hack.art.configuration;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Roman on 30.08.2015.
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() throws IOException {
        final LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setHibernateProperties(hibernateProperties());
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.hack.art.domain");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setDriverClassName(environment.getProperty("database.driver"));
        dataSource.setUsername(environment.getProperty("database.user"));
        dataSource.setDefaultAutoCommit(true);
        //dataSource.setDriverClassLoader(environment.getProperty("database.driver").getClass().getClassLoader());
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }


        public Properties hibernateProperties(){
            Properties properties = new Properties();
            properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
            properties.put("hibernate.connection.driver_class", environment.getProperty("database.driver"));
            properties.put("hibernate.show_sql", true);
            properties.put("hibernate.hbm2ddl.auto", "update");
            return properties;
        }
}
