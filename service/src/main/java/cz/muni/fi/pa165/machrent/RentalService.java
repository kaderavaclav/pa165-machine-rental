/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Machine;
import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Peter Benus
 */
@Service
public interface RentalService {
    public Rental findById(Long id);
    public List<Rental> findAll();
    public Rental createRental(Rental r);
    public void deleteRental(Rental r);
    public void setNote(Rental r, String note);
    public String getNote(Rental r);
    public void setCustomer(Rental r, RentalUser customer);
    public RentalUser getCustomer(Rental r);
    public void setEmployee(Rental r, RentalUser employee);
    public RentalUser getEmployee(Rental r);
    public void setMachine(Rental r, Machine m);
    public Machine getMachine(Rental r);
    public void setDateStart(Rental r, Date d);
    public Date getDateStart(Rental r);
    public void setDateEnd(Rental r, Date d);
    public Date getDateEnd(Rental r);
    public Date getDateCreate(Rental r);
}
