package tiny.extensions;

public class Place {
    protected String name;
    protected String address;

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }    

    public String formatToSave() {
        return "PL | " + name + " | " + address;
    }


    @Override
    public String toString() {
        return "Name: " + name + " | Address: " + address;
    }    
}
