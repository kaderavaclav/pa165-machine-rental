package cz.muni.fi.pa165.machrent.enums;

/**
 * There are only two possible types of legal person: natural person and
 * juridical person (Latin: persona ficta).
 * 
 * For further details, see Wikipedia:
 * https://en.wikipedia.org/wiki/Legal_personality
 * 
 * @author  Josef Plch
 * @since   2016-10-30
 * @version 2017-01-08
 */
public enum LegalPersonality {
    JURIDICAL ("juridical"),
    NATURAL ("natural");
    private final String userFriendlyName;
    
    private LegalPersonality (String userFriendlyName) {
        this.userFriendlyName = userFriendlyName;
    }
    
    @Override
    public String toString () {
        return this.userFriendlyName;
    }    
}
