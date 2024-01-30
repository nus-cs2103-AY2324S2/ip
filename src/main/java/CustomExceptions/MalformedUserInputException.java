package CustomExceptions;

public class MalformedUserInputException extends Exception {
    public MalformedUserInputException(String message) {
        super("There appears to be some problem with your user input.");
    }
}
