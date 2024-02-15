package tiny.extensions;

/**
 * Represents a contact.
 */
public class Contact {
    protected String name;
    protected String contactNumber;

    /**
     * Initializes Contact.
     *
     * @param name          Name of the contact.
     * @param contactNumber Contact Number of the contact.
     */
    public Contact(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String formatToSave() {
        return "CO | " + name + " | " + contactNumber;
    }

    @Override
    public String toString() {
        return "name: " + name + " | Contact Number: " + contactNumber;
    }
}
