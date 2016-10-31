package cz.muni.fi.pa165.machrent.entities;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by zuz-schwarzova on 27. 10. 2016.
 */
@Entity
@Table
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;
    private Date revisionDate;
    @OneToOne
    private RentalUser mechanic;
    @OneToOne
    private Machine machine;

    /**
    * Returns id of revision.
    *
    * @return revision id
    */
    public Long getId() {
        return id;
    }

    /**
     *  Sets id of revision.
     *
     * @param id of revision
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns note of revision.
     *
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     *  Sets the note for revision.
     *
     * @param note about revision
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Returns date of last revision.
     *
     * @return date of revision
     */
    public Date getRevisionDate() { return revisionDate; }

    /**
     *  Sets the date of revision.
     *
     * @param revisionDate of revision
     */
    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * Returns the mechanic who did the revision.
     *
     * @return mechanic
     */
    public RentalUser getMechanic() {
        return mechanic;
    }

    /**
     *  Sets the mechanic who did the revision.
     *
     * @param  mechanic who did the revision
     */
    public void setMechanic(RentalUser mechanic) {
        this.mechanic = mechanic;
    }

    /**
     * Returns the machine which was revised in this revision.
     *
     * @return revised machine
     */
    public Machine getMachine() {
        return machine;
    }

    /**
     *  Sets the machine which was revised in this revision.
     *
     * @param machine revised in this revision
     */
    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revision)) return false;

        Revision revision = (Revision) o;

        if (getRevisionDate() != null ? !getRevisionDate().equals(revision.getRevisionDate()) : revision.getRevisionDate() != null)
            return false;
        return getMachine() != null ? getMachine().equals(revision.getMachine()) : revision.getMachine() == null;

    }

    @Override
    public int hashCode() {
        int result = getRevisionDate() != null ? getRevisionDate().hashCode() : 0;
        result = 31 * result + (getMachine() != null ? getMachine().hashCode() : 0);
        return result;
    }
}

