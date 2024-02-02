package Quacky;

import java.lang.Exception;
/**
 * Represents exceptions specific to the Quacky application.
 */
public class QuackyException extends Exception {
    public QuackyException(String message) {
        super(message);
    }
}