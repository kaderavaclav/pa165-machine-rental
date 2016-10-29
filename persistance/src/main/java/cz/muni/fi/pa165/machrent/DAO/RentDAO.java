/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.Rent;
import java.util.List;

/**
 *
 * @author Peter Benus
 */
public interface RentDAO {
    public void create(Rent r);
    public void delete(Rent r); 
    public void update(Rent r); 
    public Rent findById (Long id);
    public List<Rent> findAll(); 
}
