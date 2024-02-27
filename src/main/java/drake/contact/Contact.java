package drake.contact;

/**
 * Represents a general contact.
 */
public class Contact {
    protected String name;
    protected String details;

    /**
     * Constructor for creating a Contact.
     * @param name The Contact's name.
     * @param details The Contact's details.
     */
    public Contact(String name, String details) {
        this.name = name;
        this.details = details;
    }
    /**
     * Creates a string representation of an instance of this class.
     *
     * @return The String representation of an instance of this class.
     */
    @Override
    public String toString() {
        return this.name + ": " + details;
    }
}
