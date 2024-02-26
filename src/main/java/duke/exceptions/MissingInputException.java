package duke.exceptions;

/**
 * Class representing exception for when chatbot input is missing required inputs.
 */
public class MissingInputException extends Exception {
    public MissingInputException(String message) {
        super(message);   
    }
}

