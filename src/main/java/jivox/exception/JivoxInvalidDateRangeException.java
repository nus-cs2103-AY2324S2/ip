package jivox.exception;

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
