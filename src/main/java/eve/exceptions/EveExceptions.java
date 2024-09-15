package eve.exceptions;

/**
 * This class is used to handle the exceptions that are thrown by the program
 */
public class EveExceptions extends RuntimeException {
    public EveExceptions(String error) {
        super(error);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
