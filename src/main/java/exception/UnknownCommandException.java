package exception;

public class UnknownCommandException extends Exception{
    public UnknownCommandException(String commandName) {
        super("UnknownCommandException: " + "'" + commandName + "'"
                + " is unknown.");
    }

}
