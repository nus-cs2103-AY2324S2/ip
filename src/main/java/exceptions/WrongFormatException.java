package exceptions;

public class WrongFormatException extends BaseException{
    public WrongFormatException() {
    }


    public WrongFormatException(String message) {
        super("!!!ERROR: Incorrect instruction format. The correct format is: " + message);
    }
}
