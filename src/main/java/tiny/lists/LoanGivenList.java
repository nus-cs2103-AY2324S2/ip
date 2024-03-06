package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.LoanGiven;

/**
 * Manages all the loans given.
 */
public class LoanGivenList {
    protected ArrayList<LoanGiven> loansGiven = new ArrayList<>();

    public void add(LoanGiven loan) {
        loansGiven.add(loan);
    }

    public void delete(Integer ind) {
        loansGiven.remove(loansGiven.get(ind));
    }

    public LoanGiven get(Integer ind) {
        return loansGiven.get(ind);
    }

    public Integer size() {
        return loansGiven.size();
    }

    /**
     * Lists out all the loansGiven in the list.
     *
     * @return String of all of the loansGiven.
     */
    public String list() {
        if (loansGiven.size() == 0) {
            return "You don't have any loans!";
        }
        String output = "";
        for (int i = 0; i < loansGiven.size(); i++) {
            output += (i + 1) + ". " + loansGiven.get(i);
            output += "\n";
        }
        return output;
    }


    /**
     * Formats all the loansGiven into the correct format to save.
     *
     * @return ArrayList of loansGiven in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < loansGiven.size(); i++) {
            toSave.add(loansGiven.get(i).formatToSave());
        }
        return toSave;
    }
}
