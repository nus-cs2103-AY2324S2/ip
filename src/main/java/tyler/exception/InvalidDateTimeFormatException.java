package tyler.exception;

public class InvalidDateTimeFormatException extends TylerException {
    public InvalidDateTimeFormatException() {
        super("OHNO NO NO! The format of the date should be yyyy-MM-dd HHmm");
    }
}
