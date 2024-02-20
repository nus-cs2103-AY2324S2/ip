package javassist.util;

import javassist.exception.JavAssistException;

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
     * Represents the possible categories of expenses.
     */
    public enum ExpenseCategory {
        FOOD,
        GROCERY,
        BOOKS,
        TRANSPORT,
        CLOTHES,
        ENTERTAINMENT,
        OTHER
    }


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

    /**
     * Deducts the amount of specified category.
     *
     * @param category Category to deduct from.
     * @param amount Amount to deduct.
     * @throws JavAssistException If amount is more than current expense.
     */
    public void deductExpense(ExpenseCategory category, float amount) throws JavAssistException {
        float currentExpense = getCategoryExpense(category);

        if (amount > currentExpense) {
            throw new JavAssistException("Amount to deduct is greater than initial expense!");
        }

        setCategoryExpense(category, currentExpense - amount);
    }

    /**
     * Increment the amount of specified category.
     *
     * @param category Category to add to.
     * @param amount Amount to add.
     */
    public void addExpense(ExpenseCategory category, float amount) {
        float currentExpense = getCategoryExpense(category);
        setCategoryExpense(category, currentExpense + amount);
    }

    // Used chatGPT 3.5 to generate code for managing getting expenses for all categories in one method.
    private float getCategoryExpense(ExpenseCategory category) {
        switch (category) {
        case FOOD:
            return getFood();
        case GROCERY:
            return getGrocery();
        case BOOKS:
            return getBooks();
        case TRANSPORT:
            return getTransport();
        case CLOTHES:
            return getClothes();
        case ENTERTAINMENT:
            return getEntertainment();
        case OTHER:
            return getOther();
        default:
            return 0.0f;
        }
    }

    // Used chatGPT 3.5 to generate code for managing setting expenses for all categories in one method.
    private void setCategoryExpense(ExpenseCategory category, float expense) {
        switch (category) {
        case FOOD:
            setFood(expense);
            break;
        case GROCERY:
            setGrocery(expense);
            break;
        case BOOKS:
            setBooks(expense);
            break;
        case TRANSPORT:
            setTransport(expense);
            break;
        case CLOTHES:
            setClothes(expense);
            break;
        case ENTERTAINMENT:
            setEntertainment(expense);
            break;
        case OTHER:
            setOther(expense);
            break;
        default:
        }
    }

}
