package pl.edu.wat.AI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
@Profile({ "development", "test" })

@Configuration

@EnableTransactionManagement
@EnableJpaRepositories(value= "pl.edu.wat.AI.repository", transactionManagerRef = "platformTransactionManager")
public class JpaConfig {

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("pl.edu.wat.AI.Model.Entity");
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
}
