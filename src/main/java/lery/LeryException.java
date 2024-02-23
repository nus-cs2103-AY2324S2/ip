package lery;

/**
 * Custom exception class for Lery chatbot application.
 *
 * This exception is used to handle specific errors or exceptional conditions
 * that may occur during the execution of the Lery chatbot.
 *
 * The class extends the {@code Exception} class and provides two constructors:
 * one without a message and another with a customizable error message.
 */
public class LeryException extends Exception {

    public LeryException(String message) {
        super(message);
    }

}
