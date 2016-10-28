package cz.muni.fi.pa165.machrent.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by vaclav.kadera on 27-Oct-16.
 */

@Entity
public class Machine {

    @Id
    @GeneratedValue()
    private long id;

    @NotNull
    private String name;

    private String description;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machine machine = (Machine) o;

        if (getId() != machine.getId()) return false;
        if (!getName().equals(machine.getName())) return false;
        return getDescription() != null ? getDescription().equals(machine.getDescription()) : machine.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
