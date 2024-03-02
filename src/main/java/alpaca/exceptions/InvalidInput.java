package alpaca.exceptions;

public class InvalidInput extends Exception {

    /**
     * An error when a command is called with an input which is not understood.
     **/
    public InvalidInput(String errorMessage) {
        super(errorMessage);
    }
}
