package jivox.exception;

/**
 * Represents a duke exception when the database file can not be created.
 */
public class JivoxDatabaseException extends JivoxException {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s Encountered fatal problem with the database!", super.toString());
    }
}
