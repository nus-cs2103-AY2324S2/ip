package tes.command;

/**
 * Represents an exception on wrong format of date or time in the command.
 */
public class DateAndTimeFormatterException extends Exception {
    public DateAndTimeFormatterException(String message) {
        super(message);
    }
}
