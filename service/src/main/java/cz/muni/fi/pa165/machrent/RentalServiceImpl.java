/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.dao.RentalDao;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Peter Benus
 */
@Service
public class RentalServiceImpl implements RentalService {

    @Inject
    private RentalDao rentalDao;

    @Override
    public Rental findById(Long id) {
        return rentalDao.findById(id);
    }

    @Override
    public List<Rental> findAll() {
        return rentalDao.findAll();
    }

    @Override
    public Rental createRental(Rental r) {
        r.setDateCreated(new Date());
        rentalDao.create(r);
        return r;
    }

    @Override
    public void deleteRental(Rental r) {
        rentalDao.delete(r);
    }

    @Override
    public void setNote(Rental r, String note) {
        r.setNote(note);
    }

    @Override
    public String getNote(Rental r) {
        return r.getNote();
    }

    @Override
    public void setCustomer(Rental r, RentalUser customer) {
        r.setCustomer(customer);
    }

    @Override
    public RentalUser getCustomer(Rental r) {
        return r.getCustomer();
    }

    @Override
    public void setEmployee(Rental r, RentalUser employee) {
        r.setEmployee(employee);
    }

    @Override
    public RentalUser getEmployee(Rental r) {
        return r.getEmployee();
    }

    @Override
    public void setMachine(Rental r, Machine m) {
        r.setMachine(m);
    }

    @Override
    public Machine getMachine(Rental r) {
        return r.getMachine();
    }

    @Override
    public void setDateStart(Rental r, Date d) {
        r.setDateStart(d);
    }

    @Override
    public Date getDateStart(Rental r) {
        return r.getDateStart();
    }

    @Override
    public void setDateEnd(Rental r, Date d) {
        r.setDateEnd(d);
    }

    @Override
    public Date getDateEnd(Rental r) {
        return r.getDateEnd();
    }

    @Override
    public Date getDateCreate(Rental r) {
        return r.getDateCreated();
    }

}
