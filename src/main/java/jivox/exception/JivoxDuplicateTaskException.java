package jivox.exception;

/**
 * Represents a Jivox exception when there is a task already in the database
 */
public class JivoxDuplicateTaskException extends JivoxException {


    @Override
    public String toString() {
        return String.format("%s There is a Duplicate task in your List!", super.toString());
    }
}
