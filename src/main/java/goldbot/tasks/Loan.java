package goldbot.tasks;

import java.util.ArrayList;

import goldbot.exceptions.TooMuchPaidException;

/**
 * Represents a loan task.
 */
public class Loan extends Task {
    private String loaner;
    private int amount;

    public Loan(String loaner, boolean isDone, int amount) {
        super(loaner, isDone);
        this.loaner = loaner;
        this.amount = amount;
    }

    public Loan(String loaner, int amount) {
        this(loaner, false, amount);
    }

    public String typeOfTask() {
        return "L";
    }

    public String getLoaner() {
        return this.loaner;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return String.format("Owes: %s $%d", super.getName(), this.amount);
    }

    public void pay(int money) throws TooMuchPaidException {
        assert money >= 0 : "Money paid should be non-negative";
        if (money > this.amount) {
            throw new TooMuchPaidException("You are paying too much!");
        }
        this.amount -= money;
    }

    protected ArrayList<String> exportDataAsArray() {
        ArrayList<String> data = super.exportDataAsArray();
        data.add(Integer.toString(this.amount));
        return data;
    }
}
