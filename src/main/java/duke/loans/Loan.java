package duke.loans;

import java.math.BigDecimal;

/**
 * A loan that could either be taken or given by the user.
 */
public class Loan {

    protected BigDecimal amount;
    private String details;
    private boolean isTaken;
    private boolean isReturned = false;

    /**
     * Constructs a <code>Loan</code> that keeps track of user's given / taken loans.
     *
     * @param amt Loan amount.
     * @param d The details of the loan.
     * @param taken If loan was taken by the user, it is <code>true</code>.
     *              Otherwise, if it was given, it should be <code>false</code>.
     */
    public Loan(BigDecimal amt, String d, boolean taken) {
        amount = amt;
        details = d;
        isTaken = taken;
    }

    /**
     * Constructs a loan and specifies if the loan has been returned.
     *
     * @param amt Loan amount.
     * @param d Details of the loan.
     * @param taken If loan was taken by the user, it is <code>true</code>.
     *              Otherwise, if it was given, it should be <code>false</code>.
     * @param returned Boolean if loan has been returned.
     */
    public Loan(BigDecimal amt, String d, boolean taken, boolean returned) {
        amount = amt;
        details = d;
        isTaken = taken;
        isReturned = returned;
    }

    /**
     * Sets loan as returned.
     */
    public void returnLoan() {
        isReturned = true;
    }

    /**
     * Get loan details.
     *
     * @return Loan description.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Get the loan amount in text.
     *
     * @return Loan amount.
     */
    public String getAmount() {
        return amount.toString();
    }

    /**
     * Get status on whether the loan is returned.
     *
     * @return Return status of loan.
     */
    public boolean getReturnStatus() {
        return isReturned;
    }

    /**
     * Get the loan type, either the loan was "given" or "taken" by the user.
     *
     * @return Loan type.
     */
    public String getType() {
        if (isTaken) {
            return "taken";
        }
        return "given";
    }

    @Override
    public String toString() {
        String taken = (isTaken) ? "T" : "G";
        String returned = (isReturned) ? "Returned" : " ";
        return String.format("[%s][%s] %s", returned, taken, details);
    }
}
