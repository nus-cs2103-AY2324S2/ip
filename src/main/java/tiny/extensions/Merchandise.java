package tiny.extensions;

/**
 * Represents a merchandise.
 */
public class Merchandise {
    protected String name;
    protected Integer quantity;
    protected Double price;

    /**
     * Initializes Merchandise.
     *
     * @param name     Name of the merchandise.
     * @param quantity Quantity of the merchandise.
     * @param price    Price of the merchandise.
     */
    public Merchandise(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String formatToSave() {
        return "ME | " + name + " | " + quantity + " | " + price;
    }

    @Override
    public String toString() {
        return "Merchandise Name: " + name + " | Quantity: " + quantity + " |  Price: " + price;
    }
}
