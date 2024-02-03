package duke.exceptions;

/**
 * Throws error message because of the Semantic Error in the application
 */
public class SemanticException extends DukeException{

    public SemanticException(String message) {
        super(message);
    }
}