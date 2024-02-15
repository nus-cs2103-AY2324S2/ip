package tiny.extensions;

/**
 * Represents a place.
 */
public class Place {
    protected String name;
    protected String address;

    /**
     * Initializes Place.
     *
     * @param name    Name of the place.
     * @param address Address of the place.
     */
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
