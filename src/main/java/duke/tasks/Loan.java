package duke.tasks;

import duke.exceptions.TooMuchPaidException;

/**
 * Represents a loan task.
 */
public class Loan extends Task {
    private String loaner;
    public int amount;

    public Loan(String loaner, int amount) {
        super("Owes: " + loaner + " $" + amount);
        this.loaner = loaner;
        this.amount = amount;
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

    public void pay(int money) throws TooMuchPaidException {
        assert money >= 0 : "Money paid should be non-negative";
        if (money > this.amount) {
            throw new TooMuchPaidException("You are paying too much!");
        }
        this.amount -= money;
    }
}
