package com.example.artemis;

/**
 * Custom exception class for Artemis application.
 * Represents exceptions specific to the Artemis application.
 */
public class ArtemisException extends Exception {

    /**
     * Constructs an ArtemisException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ArtemisException(String message) {
        super(message);
    }
}
