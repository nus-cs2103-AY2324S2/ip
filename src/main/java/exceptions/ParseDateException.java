package exceptions;

/**
 * Custom exception class representing the scenario where date parsing encounters an issue.
 * Exends the DukeExceptions class.
 */
public class ParseDateException extends ChatterExceptions {
    private String msg = "Please ensure that your date follows this format [MM/DD/YYYY HHMM] \n"
            + "Eg:  2/12/2019 1800";

    public ParseDateException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
