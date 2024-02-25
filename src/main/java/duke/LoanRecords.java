package duke;

import java.math.BigDecimal;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.loans.Loan;


/**
 * A Loan Record that holds all loans, whether given or taken by the user.
 */
public class LoanRecords {
    private ArrayList<Loan> loans;

    /**
     * Constructs a Loan Record to store all loans related to the user.
     *
     * @param l <code>ArrayList</code> of loans.
     */
    protected LoanRecords(ArrayList<Loan> l) {
        loans = l;
    }

    /**
     * Constructs an empty Loan Record when persistent file data is not found.
     */
    protected LoanRecords() {
        loans = new ArrayList<>();
    }

    /**
     * Specifies the loan given out by the user.
     *
     * @param st <code>Storage</code> for data persistence.
     * @param args <code>Command</code> arguments.
     * @return Output for Miku.
     * @throws DukeException If arguments are invalid.
     */
    protected String giveLoan(Storage st, String... args) throws DukeException {
        BigDecimal amount = new BigDecimal(args[1]);
        String detail = args[0];
        loans.add(new Loan(amount, detail, false));
        st.saveLoans(loans);
        return "Loan of $" + amount + " given for " + detail + "!";
    }

    /**
     * Specifies the loan taken by the user.
     *
     * @param st <code>Storage</code> for data persistence.
     * @param args <code>Command</code> arguments.
     * @return Output for Miku.
     * @throws DukeException If arguments are invalid.
     */
    protected String takeLoan(Storage st, String... args) throws DukeException {
        BigDecimal amount = new BigDecimal(args[1]);
        String detail = args[0];
        loans.add(new Loan(amount, detail, true));
        st.saveLoans(loans);
        return "Loan of $" + amount + " taken for " + detail + "!";
    }

    /**
     * Specifies the loan that has been returned.
     *
     * @param st <code>Storage</code> for data persistence.
     * @param args <code>Command</code> arguments
     * @return Output for Miku.
     * @throws DukeException If index specified is out of bounds.
     */
    protected String returnLoan(Storage st, String... args) throws DukeException {
        try {
            Loan l = loans.get(Integer.parseInt(args[0]) - 1);
            l.returnLoan();
            st.saveLoans(loans);
            return "Loan has been returned:\n"
                    + l;
        } catch (IndexOutOfBoundsException ie) {
            throw new DukeException("Index number cannot be out of bounds.");
        }
    }

    /**
     * Lists down all saved loans.
     *
     * @return Output for Miku.
     */
    protected String list() {
        int count = 0;
        String res = "Here are the loans in your list:";
        for (Loan l : loans) {
            count++;
            res = res.concat("\n" + count + ". " + l);
        }
        return res;
    }
}
