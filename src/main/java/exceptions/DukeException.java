package exceptions;

public class DukeException extends RuntimeException {

    public DukeException(String error) {
        super(error);
    }

    /**
     * {@inheritDoc}
     * Gives a description of the error.
     * @return Message on the error.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
