package jivox.exception;

/**
 * Represents a Jivox exception when the date provided by users does not follow the format.
 */
public class JivoxInvalidDateException extends JivoxException {

    @Override
    public String toString() {
        return String.format(
                "%s Please enter the start/end time in the format of <DD/MM/YY HH:MM>!\n",
                super.toString()
        );
    }
}
