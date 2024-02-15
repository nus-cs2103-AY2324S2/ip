package tiny.extensions;

public class Client {
    protected String name;
    protected String contactNumber; 
    
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
