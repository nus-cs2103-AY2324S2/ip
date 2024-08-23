package jivox.exception;

/**
 * Represents a Jivox exception when the database file can not be created.
 */
public class JivoxDatabaseException extends JivoxException {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s Unable to Connect with Database!", super.toString());
    }
}
