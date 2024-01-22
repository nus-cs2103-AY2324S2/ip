package exceptions;

public class InvalidStatusUpdateException extends LuluException {
    public InvalidStatusUpdateException() {
        super("Status was already updated to desired value");
    }
}
