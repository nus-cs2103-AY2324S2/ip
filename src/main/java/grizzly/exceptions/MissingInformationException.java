package grizzly.exceptions;

/**
 * This class implements the Exception for when user input to the bot is missing parameter information.
 *
 * @author delishad21
 */
public class MissingInformationException extends GrizzlyException {

    /**
     * Creates a MissingInformationException, thrown when a task is unable to be created due to
     * missing information.
     *
     * @param missingParameters All missing information in creation of the task.
     */
    public MissingInformationException(String... missingInformation) {
        super("Missing information: " + String.join(",", missingInformation));
    }

}
