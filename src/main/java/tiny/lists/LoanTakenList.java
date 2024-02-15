package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.LoanTaken;


/**
 * Manages all the taken loans.
 */
public class LoanTakenList {
    protected ArrayList<LoanTaken> loansTaken = new ArrayList<>();

    public void add(LoanTaken loan) {
        loansTaken.add(loan);
    }

    public void delete(Integer ind) {
        loansTaken.remove(loansTaken.get(ind));
    }

    public LoanTaken get(Integer ind) {
        return loansTaken.get(ind);
    }

    public Integer size() {
        return loansTaken.size();
    }

    /**
     * Lists out all the loansTaken in the list.
     *
     * @return String of all of the loansTaken.
     */
    public String list() {
        if (loansTaken.size() == 0) {
            return "You don't have any loans!";
        }
        String output = "";
        for (int i = 0; i < loansTaken.size(); i++) {
            output += (i + 1) + ". " + loansTaken.get(i);
            output += "\n";
        }
        return output;
    }


    /**
     * Formats all the loansTaken into the correct format to save.
     *
     * @return ArrayList of loansTaken in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < loansTaken.size(); i++) {
            toSave.add(loansTaken.get(i).formatToSave());
        }
        return toSave;
    }
}
