package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.entities.Revision;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by vaclav.kadera on 11-Dec-16.
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private MachineService machineService;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalUserService rentalUserService;

    @Autowired
    private RevisionService revisionService;


    @Override
    public void loadData() throws IOException {

        Machine m1 = machine("Machine 1", "Really machine number 1");
        Machine m2 = machine("Machine 2", "Really machine number 2");
        Machine m3 = machine("Machine 3", "Really machine number 3");
        Machine m4 = machine("Machine 4", "Really machine number 4");
        Machine m5 = machine("Machine 5", "Really machine number 5");
        Machine m6 = machine("Machine 6", "Really machine number 6");

        RentalUser e1 = rentalUser("user1", "user1@email.com", "User 1","userPass1", LegalPersonality.NATURAL);
        RentalUser e2 = rentalUser("user2", "user2@email.com", "User 2","userPass2", LegalPersonality.NATURAL);
        RentalUser e3 = rentalUser("user3", "user3@email.com", "User 3","userPass3", LegalPersonality.NATURAL);
        RentalUser c1 = rentalUser("user4", "user4@email.com", "User 4","userPass4", LegalPersonality.NATURAL);
        RentalUser c2 = rentalUser("user5", "user5@email.com", "User 5","userPass5", LegalPersonality.JURIDICAL);
        RentalUser c3 = rentalUser("user6", "user6@email.com", "User 6","userPass6", LegalPersonality.JURIDICAL);

        Revision v1 = revision(e1, m1, getDate("2016-01-02"), "note 1");
        Revision v2 = revision(e2, m1, getDate("2016-02-02"), "note 2");
        Revision v3 = revision(e3, m2, getDate("2016-03-02"), "note 3");
        Revision v4 = revision(e1, m2, getDate("2016-04-02"), "note 4");
        Revision v5 = revision(e2, m5, getDate("2016-05-02"), "note 5");
        Revision v6 = revision(e3, m6, getDate("2016-06-02"), "note 6");

        Rental r1 = rental(c1, e3, m1, "note 1", getDate("2017-01-11"), getDate("2017-01-01"), getDate("2016-01-11"));
        Rental r2 = rental(c2, e3, m2, "note 2", getDate("2017-01-12"), getDate("2017-02-02"), getDate("2016-01-12"));
        Rental r3 = rental(c3, e3, m3, "note 3", getDate("2017-01-13"), getDate("2017-03-03"), getDate("2016-01-13"));
        Rental r4 = rental(c1, e3, m4, "note 4", getDate("2017-01-14"), getDate("2017-04-04"), getDate("2016-01-14"));
        Rental r5 = rental(c2, e2, m5, "note 5", getDate("2017-01-15"), getDate("2017-05-05"), getDate("2016-01-15"));
        Rental r6 = rental(c3, e2, m6, "note 6", getDate("2017-01-16"), getDate("2017-06-06"), getDate("2016-01-16"));
        Rental r7 = rental(c1, e2, m3, "note 7", getDate("2017-01-17"), getDate("2017-07-07"), getDate("2016-01-17"));
        Rental r8 = rental(c2, e1, m2, "note 8", getDate("2017-01-18"), getDate("2017-08-08"), getDate("2016-01-18"));
        Rental r9 = rental(c3, e1, m1, "note 9", getDate("2017-01-19"), getDate("2017-09-09"), getDate("2016-01-19"));

    }

    private Machine machine(String name, String description){
        Machine m = new Machine();
        m.setName(name);
        m.setDescription(description);

        machineService.createMachine(m);
        return m;
    }

    private RentalUser rentalUser(String username, String email, String name
            , String password, LegalPersonality legalPersonality){

        RentalUser u = new RentalUser();
        u.setUsername(username);
        u.setName(name);
        u.setName(email);
        u.setLegalPersonality(legalPersonality);

        rentalUserService.registerUser(u, password);
        return u;
    }

    private Revision revision(RentalUser u, Machine m
            , Date date, String note){

        Revision r = new Revision();
        r.setMachine(m);
        r.setMechanic(u);
        r.setNote(note);
        r.setRevisionDate(date);

        revisionService.createRevision(r);
        return r;
    }

    private Rental rental(RentalUser customer, RentalUser employee
            , Machine machine, String note, Date start, Date end, Date created){

        Rental r = new Rental();
        r.setEmployee(employee);
        r.setCustomer(customer);
        r.setMachine(machine);
        r.setNote(note);
        r.setDateCreated(created);
        r.setDateStart(start);
        r.setDateEnd(end);

        rentalService.createRental(r);
        return r;
    }

    private Date getDate(String date) {

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            return formatter.parse(date);
        }
        catch (ParseException e)
        {
            Date d = new GregorianCalendar(2016,01,01).getTime();
            return d;
        }

    }
}
