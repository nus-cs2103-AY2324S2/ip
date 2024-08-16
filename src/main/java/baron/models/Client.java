package baron.models;

import baron.utils.StringUtils;

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


    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        String div = StringUtils.SEPARATOR;
        return this.studentNumber + div + this.email + div + this.name + div + this.contactNumber;
    }
}
