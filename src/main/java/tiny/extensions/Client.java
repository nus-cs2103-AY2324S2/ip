package tiny.extensions;

/**
 * Represents a client.
 */
public class Client {
    protected String name;
    protected String contactNumber;

    /**
     * Initializes Client.
     *
     * @param name          Name of the client.
     * @param contactNumber Contact Number of the client.
     */
    public Client(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String formatToSave() {
        return "CL | " + name + " | " + contactNumber;
    }

    @Override
    public String toString() {
        return "name: " + name + " | Contact Number: " + contactNumber;
    }
}
