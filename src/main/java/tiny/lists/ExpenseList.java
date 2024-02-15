package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.Expense;

/**
 * Manages all the expenses.
 */
public class ExpenseList {
    protected ArrayList<Expense> expenses = new ArrayList<>();

    public void add(Expense expense) {
        expenses.add(expense);
    }

    public void delete(Integer ind) {
        expenses.remove(expenses.get(ind));
    }

    public Expense get(Integer ind) {
        return expenses.get(ind);
    }

    public Integer size() {
        return expenses.size();
    }

    /**
     * Lists out all the expenses in the list.
     *
     * @return String of all of the expenses.
     */
    public String list() {
        if (expenses.size() == 0) {
            return "You don't have any expenses!";
        }
        String output = "";
        for (int i = 0; i < expenses.size(); i++) {
            output += (i + 1) + ". " + expenses.get(i);
            output += "\n";
        }
        return output;
    }

    /**
     * Formats all the expenses into the correct format to save.
     *
     * @return ArrayList of expenses in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < expenses.size(); i++) {
            toSave.add(expenses.get(i).formatToSave());
        }
        return toSave;
    }
}
