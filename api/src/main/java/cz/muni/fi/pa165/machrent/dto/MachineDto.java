package cz.muni.fi.pa165.machrent.dto;

/**
 * Created by vaclav.kadera on 16-Nov-16.
 */
public class MachineDto {

    private long id;

    private String name;

    private String description;

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

        MachineDto that = (MachineDto) o;

        if (getId() != that.getId()) return false;
        if (!getName().equals(that.getName())) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
