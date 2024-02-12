package aurora.objects;

/**
 * The DukeException class represents exceptions specific to this application.
 */
public class AuroraException extends Exception {

    /** Message of the exception. */
    private String exceptionMessage;

    /**
     * Constructor of the DukeException class.
     *
     * @param exceptionMessage Message of the exception that describes it.
     */
    public AuroraException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Getter for the exception message.
     *
     * @return Message that describes the exception.
     */
    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
