package jivox.exception;

/**
 * Represents a duke exception when argument(s) is/are missing for the command.
 */
public class JivoxMissingArgumentException extends JivoxException {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s You are missing argument(s) for this command!", super.toString());
    }
}
