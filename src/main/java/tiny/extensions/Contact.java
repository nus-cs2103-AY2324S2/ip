package tiny.extensions;

public class Contact {
    protected String name;
    protected String contactNumber;

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
