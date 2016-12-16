/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Rental;
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
    public List<Rental> findAllCreatedBetween(Date startDate, Date endDate){
        if (endDate.after(startDate) && startDate != null && endDate != null) {
            return rentalDao.findAllCreatedBetween(startDate, endDate);
        }
        else {
            return null;
        }
    }
    
    @Override
    public List<Rental> findAllEffectiveBetween(Date startDate, Date endDate){
        if (endDate.after(startDate) && startDate != null && endDate != null) {
            return rentalDao.findAllEffectiveBetween(startDate, endDate);
        }
        else {
            return null;
        }

    }

    @Override
    public void updateRental(Rental r) {
        rentalDao.update(r);
    }
}
