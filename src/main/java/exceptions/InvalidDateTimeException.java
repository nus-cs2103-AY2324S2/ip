package exceptions;

public class InvalidDateTimeException extends BaseException{
    public InvalidDateTimeException() {
        super("Incorrect format of date, please follow the convention: yyyy-mm-dd");
    }
}
