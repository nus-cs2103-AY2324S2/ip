package gronk;

/**
 * WrongTimeFormatException class.
 * This exception is thrown when the user types in
 * the time and date for either a Deadline or Event
 * class object in the wrong format. It prompts the
 * user with the correct format.
 */
public class WrongTimeFormatException extends Exception {
    public WrongTimeFormatException() {}

    public WrongTimeFormatException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "\tError, please input the time in yyyy-MM-dd HH:mm format.";
    }
}
