package simpli.exceptions;

public class SimpliException extends Exception {
    public SimpliException() {
        super();
    }

    public SimpliException(String errorMsg) {
        super(errorMsg);
    }
}
