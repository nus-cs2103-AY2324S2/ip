package exceptions;

public class AlreadyExistsException extends KaiYapException {
    public AlreadyExistsException(String errorMsg) {
        super(errorMsg);
    }
}
