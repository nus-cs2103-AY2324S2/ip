package jivox.exception;

/**
 * Represents a Jivox exception when an invalid argument is provided for the command.
 */

public class JivoxInvalidArgumentException extends JivoxException {

    @Override
    public String toString() {
        return String.format("%s invalid argument(s) for this command!", super.toString());
    }
}
