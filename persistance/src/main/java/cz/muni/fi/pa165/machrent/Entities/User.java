package cz.muni.fi.pa165.machrent.Entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/*
 * @author  Josef Plch
 * @since   2016-10-26
 * @version 2016-10-30
 */
@Entity
@Table (name = "RenatalUser")
public class User implements Serializable {
    @Column (nullable = false, unique = true)
    @NotNull
    private String email;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private LegalPersonality legalPersonality;
    
    @NotNull
    private String name;
    
    private String passwordHash;
    
    private Set <Role> roles;
    
    private static final String HASH_SALT = "VOLZvQjWGJndyOnjZTfH";
    
    @NotNull
    private String username;
    
    /**
     * There are only two possible types of legal person: natural person and
     * juridical person (Latin: persona ficta).
     * 
     * For further details, see Wikipedia:
     * https://en.wikipedia.org/wiki/Legal_personality
     * 
     * @author  Josef Plch
     * @since   2016-10-30
     * @version 2016-10-30
     */
    public enum LegalPersonality {
        JURIDICAL, NATURAL
    }
    
    /**
     * List of all possible user roles in the evidence system.
     * 
     * @author  Josef Plch
     * @since   2016-10-26
     * @version 2016-10-26
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
        else if (! (object instanceof User)) {
            areEqual = false;
        }
        else {
            User other = (User) object;
            areEqual =
                Objects.equals    (this.id,               other.id)
                && Objects.equals (this.email,            other.email)
                && Objects.equals (this.legalPersonality, other.legalPersonality)
                && Objects.equals (this.name,             other.name)
                && Objects.equals (this.passwordHash,     other.passwordHash)
                && Objects.equals (this.roles,            other.roles)
                && Objects.equals (this.username,         other.username);
        }
        return areEqual;
    }
    
    public String getEmail () {
        return this.email;
    }
    
    public Long getId () {
        return this.id;
    }
    
    public LegalPersonality getLegalPersonality () {
        return this.legalPersonality;
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
        hash = 67 * hash + (this.legalPersonality != null ? this.legalPersonality.hashCode () : 0);
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

    public void setLegalPersonality (LegalPersonality legalPersonality) {
        this.legalPersonality = legalPersonality;
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
        String string =
            "User {"
            + "id = " + this.id
            + ", email = " + this.email
            + ", name = " + this.name
            + "}";
        return string;
    }
    
    public boolean verifyPassword (String password) {
        return Objects.equals (hashPassword (password), passwordHash);
    }
}
