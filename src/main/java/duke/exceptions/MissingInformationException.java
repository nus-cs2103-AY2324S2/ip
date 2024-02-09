package duke.exceptions;

/**
 * This class implements the Exception for when user input to the bot is missing parameter information.
 *
 * @author delishad21
 */
public class MissingInformationException extends TaskCreationException {

    /**
     * Creates a TaskInformationException, thrown when a user task is unable to be created.
     *
     * @param missingParameters All missing information in creation of the task.
     */
    public MissingInformationException(String... missingInformation) {
        super("Missing information: " + String.join(",", missingInformation));
    }

}
