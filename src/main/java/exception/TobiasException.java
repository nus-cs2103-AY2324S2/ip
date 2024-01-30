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
    public void printMessage() {
        String divider = "  =======================================================================================";
        System.out.println(divider);
        System.out.println(getMessage());
        System.out.println(divider);
    }
}
