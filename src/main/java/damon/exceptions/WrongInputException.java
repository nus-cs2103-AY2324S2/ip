package damon.exceptions;

/**
 * Represents Exception corresponding to that user's input is not understandable.
 */
public class WrongInputException extends DamonExceptions{

    /**
     * Constructs a new WrongInputException object.
     */
    public WrongInputException() {
        this.message = "Sorry, I cannot understand what you mean.";
    }
}
