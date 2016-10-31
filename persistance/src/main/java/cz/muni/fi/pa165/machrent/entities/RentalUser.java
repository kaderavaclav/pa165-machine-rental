package cz.muni.fi.pa165.machrent.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
 * @author  Josef Plch
 * @since   2016-10-26
 * @version 2016-10-28
 */
@Entity
@Table
public class RentalUser implements Serializable {
    @Column (nullable = false, unique = true)
    @NotNull
    private String email;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    private String passwordHash;

    @ElementCollection
    @Enumerated
    private Set<Role> roles;
    
    private static final String HASH_SALT = "VOLZvQjWGJndyOnjZTfH";
    
    @NotNull
    private String username;
    
    /**
     * List of all possible user roles.
     */
    public enum Role {
        CUSTOMER, EMPLOYEE
    }

    @Override
    public boolean equals (Object object) {
        boolean areEqual;
        if (this == object) {
            areEqual = true;
        }
        else if (object == null) {
            areEqual = false;
        }
        else if (! (object instanceof RentalUser)) {
            areEqual = false;
        }
        else {
            RentalUser other = (RentalUser) object;
            areEqual =
                Objects.equals    (this.id,           other.id)
                && Objects.equals (this.email,        other.email)
                && Objects.equals (this.name,         other.name)
                && Objects.equals (this.passwordHash, other.passwordHash)
                && Objects.equals (this.roles,        other.roles)
                && Objects.equals (this.username,     other.username);
        }
        return areEqual;
    }
    
    public String getEmail () {
        return this.email;
    }
    
    public Long getId () {
        return this.id;
    }
    
    public String getName () {
        return this.name;
    }
    
    public String getPasswordHash () {
        return this.passwordHash;
    }

    public Set <Role> getRoles () {
        return this.roles;
    }

    public String getUsername () {
        return this.username;
    }

    @Override
    public int hashCode () {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode () : 0);
        hash = 67 * hash + (this.email != null ? this.email.hashCode () : 0);
        hash = 67 * hash + (this.name != null ? this.name.hashCode () : 0);
        hash = 67 * hash + (this.passwordHash != null ? this.passwordHash.hashCode () : 0);
        hash = 67 * hash + (this.roles != null ? this.roles.hashCode () : 0);
        hash = 67 * hash + (this.username != null ? this.username.hashCode () : 0);
        return hash;
    }
    
    private static String hashPassword (String password) {
        return Integer.toString (hashString (password + HASH_SALT));
    }
    
    private static int hashString (String string) {
        int hash;
        if (string == null) {
            hash = 0;
        }
        else {
            hash = 7;
            for (int i = 0; i < string.length (); i++) {
                hash = hash * 31 + string.charAt (i);
            }
        }
        return hash;
    }
    
    public void setId (Long id) {
        this.id = id;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public void setName (String name) {
        this.name = name;
    }
    
    public void setPassword (String password) {
        setPasswordHash (hashPassword (password));
    }
    
    public void setPasswordHash (String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRoles (Set <Role> roles) {
        this.roles = roles;
    }

    public void setUsername (String username) {
        this.username = username;
    }
    
    @Override
    public String toString () {
        return "User {id = " + this.id + ", name = " + this.name + "}";
    }
    
    public boolean verifyPassword (String password) {
        return Objects.equals (hashPassword (password), passwordHash);
    }
}
