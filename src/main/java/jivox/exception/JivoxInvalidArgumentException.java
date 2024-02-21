package jivox.exception;

/**
 * Represents a duke exception when an invalid argument is provided for the command.
 */
public class JivoxInvalidArgumentException extends JivoxException {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s You have invalid argument(s) for this command!", super.toString());
    }
}
