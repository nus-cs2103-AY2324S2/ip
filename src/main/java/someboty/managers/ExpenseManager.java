package someboty.managers;

import java.util.ArrayList;

import someboty.expenses.Expense;

/**
 * ExpenseManager is a class that handles actions related to expenses.
 * The expense list will be passed to fileManager to be stored into a csv file for future sessions.
 */
public class ExpenseManager {
    
    private ArrayList<Expense> expenseList;
    private FileManager file;

    // Constructof for ExpenseManager.
    public ExpenseManager(FileManager file) {
        this.file = file;
        this.expenseList = this.file.fetchExpenses();
    }

    /**
     * Returns the current size of the expense list.
     * 
     * @return Current size of the list as an integer.
     */
    protected int getListSize() {
        return this.expenseList.size();
    }

    /**
     * Returns a String representation of the formatted expense list.
     * The expense list is formatted to be reader friendly.
     * 
     * @return A formatted String displaying all the expenses.
     */
    protected String printList() {
        if (expenseList.size() == 0) {    // special message for empty list
            return "Master seems to have spent nothing...";
        }

        String response = "Izuna has found these tasks:\n";

        int index = 0;
        for (Expense expense : expenseList) {
            index++;
            response += String.format("%d. %s\n", index, expense);
        }

        return response;
    }

    protected void clear() {
        this.expenseList.clear();
    }

    /**
     * Adds a new expense into the list.
     * 
     * @param type The type of the new task.
     * @param description Details of the new task, including its name and (if any) dates.
     * @return Successfully created new task.
     */
    protected Expense addExpense(String description) {
        Expense newExpense = Expense.createExpense(description);
        assert (newExpense != null) : "Failed to create a new task.";

        this.expenseList.add(newExpense);
        return newExpense;
    }

    /**
     * Passes the current expense list into fileManager to save to csv file.
     */
    protected void update() {
        boolean isSuccessful = this.file.storeExpenses(this.expenseList);
        assert isSuccessful : "Unable to store the expenses into the 'expenses.csv' file.";
    }
}
