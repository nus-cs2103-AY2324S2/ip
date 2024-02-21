package jivox.exception;

/**
 * Represents a Jivox exception when argument(s) is/are missing for the command.
 */
public class JivoxMissingArgumentException extends JivoxException {

    @Override
    public String toString() {
        return String.format("%s missing argument(s) for this command!", super.toString());
    }
}
