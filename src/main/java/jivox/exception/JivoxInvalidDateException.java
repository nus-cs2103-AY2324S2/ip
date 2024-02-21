package jivox.exception;

/**
 * Represents a duke exception when the date provided by users does not follow the format.
 */
public class JivoxInvalidDateException extends JivoxException {
    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format(
                "%s Please enter the start/end time in the format of <DD/MM/YY HH:MM>!\n",
                super.toString()
        );
    }
}
