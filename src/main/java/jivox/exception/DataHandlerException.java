package jivox.exception;

public class DataHandlerException extends JivoxException {
    public DataHandlerException(String message) {
        super("Database Error |" + message);
    }
}
