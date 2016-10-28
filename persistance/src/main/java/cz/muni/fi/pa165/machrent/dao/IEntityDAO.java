package cz.muni.fi.pa165.machrent.dao;

/**
 * Created by vaclav.kadera on 27-Oct-16.
 */
public interface IEntityDAO {

    void create(IEntityDAO entity);

    void update(IEntityDAO entity);

    void remove(IEntityDAO entity);

    IEntityDAO findById(int id);

}
