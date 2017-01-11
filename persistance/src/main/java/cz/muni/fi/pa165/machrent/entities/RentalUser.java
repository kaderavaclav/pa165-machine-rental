package cz.muni.fi.pa165.machrent.entities;

import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * RentalUser represent users of the evidence system - both employees and
 * customers (they can be distinguished by attr�bute "roles").
 *
 * @author  Josef Plch
 * @since   2016-10-26
 * @version 2016-11-21
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

    @Column(nullable = false)
    @NotNull
    private LegalPersonality legalPersonality;

    @Column(nullable = false)
    @NotNull
    private String name;
    
    private String passwordHash;

    @ElementCollection
    @Enumerated
    private Set <RentalUserRole> roles;
    
    private static final String HASH_SALT = BCrypt.gensalt();

    @Column(nullable = false)
    @NotNull
    private String username;

    @Override
    public boolean equals (Object object) {
        boolean result;
        if (object == null || ! (object instanceof RentalUser)) {
            result = false;
        }
        else {
            RentalUser other = (RentalUser) object;
            result =
                Objects.equals    (this.id,               other.id)
                && Objects.equals (this.email,            other.email)
                && Objects.equals (this.legalPersonality, other.legalPersonality)
                && Objects.equals (this.name,             other.name)
                && Objects.equals (this.passwordHash,     other.passwordHash)
                && Objects.equals (this.roles,            other.roles)
                && Objects.equals (this.username,         other.username);
        }
        return result;
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

    public Set <RentalUserRole> getRoles () {
        return this.roles;
    }

    public String getUsername () {
        return this.username;
    }

    @Override
    public int hashCode () {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode (id);
        hash = 67 * hash + Objects.hashCode (email);
        hash = 67 * hash + Objects.hashCode (legalPersonality);
        hash = 67 * hash + Objects.hashCode (name);
        hash = 67 * hash + Objects.hashCode (passwordHash);
        hash = 67 * hash + Objects.hashCode (roles);
        hash = 67 * hash + Objects.hashCode (username);
        return hash;
    }
    
    private static String hashPassword (String password) {
        return BCrypt.hashpw(password, HASH_SALT); 
        //return Integer.toString (hashString (password + HASH_SALT));
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
    
    public boolean isEmployee () {
        return this.roles.contains (RentalUserRole.EMPLOYEE);
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
    
    /**
     * Actually, this method does not set a password, but rather its hash. For
     * security reasons, the password itself is not stored at all.
     * 
     * @param password new password
     */
    public void setPassword (String password) {
        setPasswordHash (hashPassword (password));
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
    
    /**
     * Compare hash of the given password with the correct hash.
     * 
     * @param password A password to be verified.
     * @return True = correct, false = incorrect.
     */
    public boolean verifyPassword (String password) {
        return Objects.equals (hashPassword (password), passwordHash);
    }

    public boolean isAdmin() {
        if (roles.contains(RentalUserRole.EMPLOYEE))
            return true;
        else
            return false;
    }
}
