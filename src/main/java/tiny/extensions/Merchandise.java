package tiny.extensions;

public class Merchandise {
    protected String name;
    protected Integer quantity;
    protected Double price;

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
