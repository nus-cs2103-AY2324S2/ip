package javassist.util;

/**
 * Represents a list of expenses.
 */
public class ExpenseList implements JavAssistList {
    private float food;
    private float transport;
    private float grocery;
    private float books;
    private float clothes;
    private float entertainment;
    private float other;

    /**
     * Constructs an ExpenseList instance by initialising all expenses to zero.
     */
    public ExpenseList() {
        this.food = 0;
        this.transport = 0;
        this.grocery = 0;
        this.books = 0;
        this.clothes = 0;
        this.entertainment = 0;
        this.other = 0;
    }

    /**
     * Constructs an ExpenseList instance by providing the value for each category.
     *
     * @param food Expense on food.
     * @param transport Expense on transport.
     * @param grocery Expense on grocery.
     * @param books Expense on books.
     * @param clothes Expense on clothes.
     * @param entertainment Expense on entertainment.
     * @param other Expense on others.
     */
    public ExpenseList(float food, float transport, float grocery, float books,
                       float clothes, float entertainment, float other) {
        this.food = food;
        this.transport = transport;
        this.grocery = grocery;
        this.books = books;
        this.clothes = clothes;
        this.entertainment = entertainment;
        this.other = other;
    }

    /**
     * Calculates the sum of all expenses.
     *
     * @return Formatted String of calculated sum.
     */
    public String calculateSum() {
        float sum = food + transport + grocery + books + clothes + entertainment + other;
        return String.format("%.2f", sum);
    }

    /**
     * Returns String of all categories and the corresponding expense.
     *
     * @return String of details on expenses.
     */
    @Override
    public String print() {
        return "Food: $" + String.format("%.2f\n", food)
                + "Transport: $" + String.format("%.2f\n", transport)
                + "Grocery: $" + String.format("%.2f\n", grocery)
                + "Books: $" + String.format("%.2f\n", books)
                + "Clothes: $" + String.format("%.2f\n", clothes)
                + "Entertainment: $" + String.format("%.2f\n", entertainment)
                + "Other: $" + String.format("%.2f", other);
    }

    @Override
    public String toString() {
        return food + " | " + transport + " | " + grocery + " | "
                + books + " | " + clothes + " | " + entertainment + " | " + other;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public void setTransport(float transport) {
        this.transport = transport;
    }

    public void setGrocery(float grocery) {
        this.grocery = grocery;
    }

    public void setBooks(float books) {
        this.books = books;
    }

    public void setClothes(float clothes) {
        this.clothes = clothes;
    }

    public void setEntertainment(float entertainment) {
        this.entertainment = entertainment;
    }

    public void setOther(float other) {
        this.other = other;
    }

    public float getFood() {
        return food;
    }

    public float getTransport() {
        return transport;
    }

    public float getGrocery() {
        return grocery;
    }

    public float getBooks() {
        return books;
    }

    public float getClothes() {
        return clothes;
    }

    public float getEntertainment() {
        return entertainment;
    }

    public float getOther() {
        return other;
    }
}
