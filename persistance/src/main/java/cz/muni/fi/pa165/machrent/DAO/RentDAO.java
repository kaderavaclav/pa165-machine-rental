/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.RentEntity;
import java.util.List;

/**
 *
 * @author Peter Benus
 */
public interface RentDAO {
    public void create(RentEntity r);
    public void delete(RentEntity r); 
    public void update(RentEntity r); 
    public List<RentEntity> get(RentEntity r); 
}
