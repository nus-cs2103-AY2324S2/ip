package baron.models;

/**
 * Contains basic information about clients.
 */
public class Client extends BaseModel {
    private String studentNumber;
    private String email;
    private String name;
    private String contactNumber;

    /**
     * Creates a client without any ID.
     * @param studentNumber e.g. A01234567X
     * @param email Email of client
     * @param name Name of client
     * @param contactNumber Contact number of client
     */
    public Client(String studentNumber, String email, String name, String contactNumber) {
        super();
        this.studentNumber = studentNumber;
        this.email = email;
        this.name = name;
        this.contactNumber = contactNumber;
    }
    /**
     * Initialises a Client object.
     * @param id id of the student record.
     * @param studentNumber e.g. A01234567X
     * @param email Email of client
     * @param name Name of client
     * @param contactNumber Contact number of client
     */
    public Client(int id, String studentNumber, String email, String name, String contactNumber) {
        super(id);
        this.studentNumber = studentNumber;
        this.email = email;
        this.name = name;
        this.contactNumber = contactNumber;
    }
}
