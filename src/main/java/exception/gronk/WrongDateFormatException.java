package exception.gronk;

/**
 * WrongTimeFormatException class.
 * This exception is thrown when the user types in
 * the time and date for either a Deadline or Event
 * class object in the wrong format. It prompts the
 * user with the correct format.
 */
public class WrongDateFormatException extends Exception {
    public WrongDateFormatException() {}

    public WrongDateFormatException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "Error, please input the time in DD/MM/YYYY format.";
    }
}
