package cz.muni.fi.pa165.machrent.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.util.Date;

/**
 * Created by Falka on 27. 10. 2016.
 */
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String note;
    private Timestamp revisionDate;
    private User mechanic;
    private Machine machine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public User getMechanic() {
        return mechanic;
    }

    public void setMechanic(User mechanic) {
        this.mechanic = mechanic;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }


}
