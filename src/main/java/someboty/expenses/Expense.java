package someboty.expenses;

import someboty.exceptions.InputException;

/**
 * Expense class stores and parses data relevant to expenses.
 */
public class Expense {
    
    private String name;
    private float amount;

    // constructor for Expense
    public Expense(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public float getAmount() {
        return this.amount;
    }

    /**
     * Creates a new expense with the given details read from the csv file.
     * 
     * @param line String of a line in the csv file.
     * @return An expense with the given details.
     */
    public static Expense csvToExpense(String line) {
        String[] details = line.split(",");
        assert details.length >= 2 : "Expense::csvToTask  A csv line is broken.";

        String name = details[0];
        float amount = Float.parseFloat(details[1]);

        return new Expense(name, amount);
    }

    /**
     * Creates a new expense with given description read.
     * 
     * @param description String of the expense details.
     * @return An expense with the given description.
     */
    public static Expense createExpense(String description) {
        String[] details = description.trim().split(" ");
        
        try {
            String name = details[0];
            float amount = Float.parseFloat(details[1]);
            return new Expense(name, amount);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputException("Master has entered in a format Izuna does not understand...");
        } catch (NumberFormatException e) {
            throw new InputException("Izuna does not understand the amount of the expense master has just entered...");
        }
    }

    public String expenseToCsv() {
        return String.format("%s,%.2f\n", this.name, this.amount);
    }

    @Override
    public String toString() {
        int nameWidth = 12;
        String formatName;

        if (this.name.length() > nameWidth) {
            formatName = this.name.substring(0, nameWidth - 3) + "...";
        } else {
            formatName = this.name;

            // pad name with (nameWidth - length) spaces.
            for (int i = this.name.length(); i < nameWidth; i++) {
                formatName += " ";
            }
        }

        return String.format("%s (%.2f)", formatName, this.amount);
    }
}
