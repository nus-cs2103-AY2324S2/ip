package jivox.exception;

/**
 * Represents a Jivox exception when the date range are wrong
 */
public class JivoxInvalidDateRangeException extends JivoxException {
    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s End time must be after the start time!", super.toString());
    }
}
