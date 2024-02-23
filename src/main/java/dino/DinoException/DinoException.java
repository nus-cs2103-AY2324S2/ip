package dino.DinoException;

/**
 * The DukeException class is a custom exception class in Java that extends the Exception class and allows for
 * the creation of exceptions with custom error messages.
 */
public class DinoException extends Exception{

    public DinoException(String message) {
        super(message);
    }

}
