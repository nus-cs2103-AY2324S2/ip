package exceptions;

public class IllegalCommandException extends BluException {
    public IllegalCommandException(String message) {
        super("Illegal Command Format: " + message);
    }   
}
