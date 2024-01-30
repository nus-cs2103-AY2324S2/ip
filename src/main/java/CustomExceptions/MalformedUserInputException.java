package CustomExceptions;

public class MalformedUserInputException extends Exception {
    public MalformedUserInputException(String message) {
        super(message);
    }
}
