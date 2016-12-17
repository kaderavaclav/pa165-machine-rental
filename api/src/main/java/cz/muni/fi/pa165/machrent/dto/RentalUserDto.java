package cz.muni.fi.pa165.machrent.dto;

import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import java.util.Objects;
import java.util.Set;

/**
 * Data transfer object (DTO) for RentalUser. No business logic, just the data.
 * 
 * @author  Josef Plch
 * @since   2016-11-19
 * @version 2016-11-20
 */
public class RentalUserDto {
    private String email;
    private Long id;
    private LegalPersonality legalPersonality;
    private String name;
    private String passwordHash;
    private Set <RentalUserRole> roles;
    private String username;
    private boolean isAdmin;

    @Override
    public boolean equals (Object object) {
        Boolean result;
        if (object == null || ! (object instanceof RentalUserDto)) {
            result = false;
        }
        else {
            final RentalUserDto other = (RentalUserDto) object;
            result =
                Objects.equals (this.email, other.email)
                && Objects.equals (this.id, other.id)
                && Objects.equals (this.legalPersonality, other.legalPersonality)
                && Objects.equals (this.name, other.name)
                && Objects.equals (this.passwordHash, other.passwordHash)
                && Objects.equals (this.roles, other.roles)
                && Objects.equals (this.username, other.username);
        }
        return result;
    }
    
    public String getEmail () {
        return email;
    }

    public Long getId () {
        return id;
    }

    public LegalPersonality getLegalPersonality () {
        return legalPersonality;
    }

    public String getName () {
        return name;
    }

    public String getPasswordHash () {
        return passwordHash;
    }

    public Set <RentalUserRole> getRoles () {
        return roles;
    }

    public String getUsername () {
        return username;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }

    @Override
    public int hashCode () {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode (email);
        hash = 89 * hash + Objects.hashCode (id);
        hash = 89 * hash + Objects.hashCode (legalPersonality);
        hash = 89 * hash + Objects.hashCode (name);
        hash = 89 * hash + Objects.hashCode (passwordHash);
        hash = 89 * hash + Objects.hashCode (roles);
        hash = 89 * hash + Objects.hashCode (username);
        return hash;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public void setLegalPersonality (LegalPersonality legalPersonality) {
        this.legalPersonality = legalPersonality;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setPasswordHash (String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRoles (Set <RentalUserRole> roles) {
        this.roles = roles;
    }

    public void setUsername (String username) {
        this.username = username;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString () {
        String string =
            "RentalUserDto {"
                + "email=" + email
                + ", id=" + id
                + ", legalPersonality=" + legalPersonality
                + ", name=" + name
                + ", passwordHash=" + passwordHash
                + ", roles=" + roles
                + ", username=" + username
            + '}';
        return string;
    }
}
