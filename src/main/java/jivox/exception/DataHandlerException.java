package jivox.exception;

/**
 * DataHandlerException is a class of exception
 * specially for DataHandler i.e Loading and Saving tasks
 */
public class DataHandlerException extends JivoxException {
    public DataHandlerException(String message) {
        super("Database Error |" + message);
    }
}
