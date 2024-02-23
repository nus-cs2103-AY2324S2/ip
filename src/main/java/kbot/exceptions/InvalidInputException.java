package kbot.exceptions;

/**
 * Encapsulates exception where parameters for the command is not valid.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class InvalidInputException extends KException {
    /**
     * Constructor for InvalidInputException.
     * 
     * @param message Prints the message when exception is thrown.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
