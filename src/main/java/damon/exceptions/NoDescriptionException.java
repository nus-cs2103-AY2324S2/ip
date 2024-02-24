package damon.exceptions;

/**
 * Represents Exception corresponding to that
 * there is the lack of Task description in user's input
 */
public class NoDescriptionException extends DamonExceptions{

    /**
     * Constructs a new NoDescriptionException object.
     */
    public NoDescriptionException() {
        this.message = "Pls add description of this task, thank you! :)";
    }
}
