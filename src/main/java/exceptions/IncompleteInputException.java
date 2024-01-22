package exceptions;

public class IncompleteInputException extends LuluException{
    public IncompleteInputException() {
        super("Missing arguments after command prompt");
    }
}
