package cz.muni.fi.pa165.machrent.config;

import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by vaclav.kadera on 22-Nov-16.
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackages = {"cz.muni.fi.pa165.machrent"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        return new DozerBeanMapper();
    }

}
