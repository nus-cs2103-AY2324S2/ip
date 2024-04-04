package exception;

public class TobiasException extends Exception {
    /**
     * Constructor for TobiasException.
     *
     * @param message Message that will be printed for the instance of TobiasException.
     * */
    public TobiasException(String message) {
        super(message);
    }

    /**
     * Prints the message of the instance of TobiasException in the console with dividers.
     * */
    public String printMessage() {
        String divider = "====================================";

        String result = divider
                + System.lineSeparator()
                + getMessage()
                + System.lineSeparator()
                + divider;

        return result;
    }
}
