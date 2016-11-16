package cz.muni.fi.pa165.machrent;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Created by vaclav.kadera on 16-Nov-16.
 */
public interface BeanMappingService {

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);

    public Mapper getMapper();
}