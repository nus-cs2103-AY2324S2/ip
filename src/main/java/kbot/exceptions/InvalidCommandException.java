package kbot.exceptions;

/**
 * Encapsulates exception where command is not valid.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class InvalidCommandException extends KException {
    /**
     * Constructor for InvalidCommandException.
     * 
     * @param message Prints out a message when exception is thrown.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
