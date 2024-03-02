package grizzly.exceptions;

/**
 * This class implements the Exception for when user input to the bot is missing parameters.
 *
 * @author delishad21
 */
public class MissingParameterException extends GrizzlyException {

    /**
     * Creates a TaskCreationException, thrown when a task is unable to be created.
     *
     * @param missingParameters All missing parameters in creation of the task.
     */
    public MissingParameterException(String... missingParameters) {
        super("Missing parameters: " + String.join(",", missingParameters));
    }

}
