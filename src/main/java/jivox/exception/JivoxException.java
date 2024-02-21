package jivox.exception;

/**
 * Abstract representation of all duke exceptions.
 */
public abstract class JivoxException extends Exception {


    @Override
    public String toString() {
        return String.format(" ('-') Oops!");
    }
}

