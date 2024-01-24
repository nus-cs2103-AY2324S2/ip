package CustomExceptions;

public class MalformedUserInputException extends Exception {
    public MalformedUserInputException(){
        super("There appears to be some problem with your user input.");
    }
}
