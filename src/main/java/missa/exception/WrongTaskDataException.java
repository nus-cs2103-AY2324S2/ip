package missa.exception;

/**
 * Alerts users when data file contains wrong info.
 */
public class WrongTaskDataException extends Exception {
    public WrongTaskDataException() {
        super("OOPS, data is stored incorrectly!");
    }
}
