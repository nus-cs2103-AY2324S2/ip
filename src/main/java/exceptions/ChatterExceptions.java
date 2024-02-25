package exceptions;

/**
 * The base exception class for Duke, representing various exceptions that can occur within the application.
 * Extends the Exception class.
 */
public class ChatterExceptions extends Exception {

    /**
     * Default constructor for the DukeExceptions class.
     */
    public ChatterExceptions() {
        super();
    }

    /**
     * Method to generate the output message for exceptions.
     *
     * @return A string indicating an error has occurred.
     */
    public String output() {
        return "Oh no! ";
    }
}
