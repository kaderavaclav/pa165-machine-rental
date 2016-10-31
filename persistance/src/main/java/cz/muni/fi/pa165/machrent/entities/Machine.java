package cz.muni.fi.pa165.machrent.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Machine class represents entity {@code Machine} in Machine Rental application.
 * @author vaclav.kadera
 */

@Entity
@Table
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    private String description;

    /**
     * Default constructor.
     */
    public Machine(){ }

    /**
     * Method that returns {@code Machine.Id}.
     * @return {@code Long id}
     */
    public long getId() {
        return id;
    }

    /**
     * Method that sets {@code Machine.Id}
     * @param id {@code Long} id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method that returns {@code Machine.Name}.
     * @return {@code String name}
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets {@code Machine.Name}
     * @param name {@code String} name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that returns {@code Machine.Description}.
     * @return {@code String description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that sets {@code Machine.Description}
     * @param description {@code String} description.
     */
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
