package tsundere.exception;

/**
 * Encapsulates an Exception that contains a general message.
 */
public class GeneralException extends Exception {

    protected final String msg;

    public GeneralException(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "Hey, watch it! " + this.msg;
    }
}
