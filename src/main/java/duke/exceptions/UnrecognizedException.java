package duke.exceptions;

/**
 * Class representing exception when chatbot receives input that does not have recognized commands.
 */
public class UnrecognizedException extends Exception {
    public UnrecognizedException(String message) {
        super(message);
    }
}
