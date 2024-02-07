package nihao.exception;

public class IllegalArgumentException extends Exception{
    public IllegalArgumentException(String commandName, int expected) {
        super("IllegalArgumentException: " + commandName + " expects " + expected + " arguments.");
    }

    public IllegalArgumentException(String errorMessage) {
        super("IllegalArgumentException: " + errorMessage + ".");
    }
}
