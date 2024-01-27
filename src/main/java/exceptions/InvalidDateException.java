package exceptions;

public class InvalidDateException extends LuluException{
    public InvalidDateException() {
        super("Invalid date detected");
    }
}
