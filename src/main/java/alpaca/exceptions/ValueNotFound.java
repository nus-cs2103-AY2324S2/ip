package alpaca.exceptions;

public class ValueNotFound extends Exception {

    /**
    *  An error when a command is called without any information if said information
    *  is required.
    **/
    public ValueNotFound(final String errorMessage) {
        super(errorMessage);
    }
}
