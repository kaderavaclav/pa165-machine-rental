package cz.muni.fi.pa165.machrent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.sql.DataSource;

import cz.muni.fi.pa165.machrent.dao.MachineDao;
import cz.muni.fi.pa165.machrent.dao.RevisionDao;
import cz.muni.fi.pa165.machrent.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import cz.muni.fi.pa165.machrent.dao.RentalDao;

/**
 *
 * @author Peter Benus
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = {"cz.muni.fi.pa165.machrent.dao"})
public class PersistenceApplicationContext {

    @Bean
    public JpaTransactionManager transactionManager(){
        return  new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean  entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean jpaFactoryBean = new LocalContainerEntityManagerFactoryBean ();
        jpaFactoryBean.setDataSource(db());
        jpaFactoryBean.setLoadTimeWeaver(instrumentationLoadTimeWeaver());
        jpaFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return jpaFactoryBean;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        return new LocalValidatorFactoryBean();
    }
    @Bean
    public LoadTimeWeaver instrumentationLoadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public DataSource db(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY).build();
        return db;
    }
}

