package exception;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String errMsg) {
        super(errMsg);
    }
}
