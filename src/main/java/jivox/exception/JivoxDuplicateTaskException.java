package jivox.exception;

/**
 * Represents a Jivox exception when there is a task already in the database
 */
public class JivoxDuplicateTaskException extends JivoxException {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s There is a duplicate task in your list!", super.toString());
    }
}
