package kz.toDoList.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${db.driver}")
    private String databaseDriver;

    @Value("${db.url}")
    private String databaseUrl;

    @Value("${db.username}")
    private String databaseUsername;

    @Value("${db.password}")
    private String databasePassword;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show-sql}")
    private String hibernateShowSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2DdlAuto;

    @Value("${db.connection-pool.initial-size}")
    private int initialSize;

    @Value("${db.connection-pool.min-idle}")
    private int minIdle;

    @Value("${db.connection-pool.max-idle}")
    private int maxIdle;

    @Value("${db.connection-pool.max-total}")
    private int maxTotal;

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(databaseDriver);
        ds.setUrl(databaseUrl);
        ds.setUsername(databaseUsername);
        ds.setPassword(databasePassword);

        ds.setInitialSize(initialSize);
        ds.setMinIdle(minIdle);
        ds.setMaxIdle(maxIdle);
        ds.setMaxTotal(maxTotal);

        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("kz.toDoList.entity");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(getHibernateProperties());

        factory.setEntityManagerFactoryInterface(EntityManagerFactory.class); // ✅ add this

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory);
        return manager;
    }

    private Properties getHibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", hibernateDialect);
        props.setProperty("hibernate.show_sql", hibernateShowSql);
        props.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);
        return props;
    }
}
