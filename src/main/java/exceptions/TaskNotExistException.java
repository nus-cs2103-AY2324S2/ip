package exceptions;

public class TaskNotExistException extends DukeException {

    public TaskNotExistException(String number) {
        super("Task number " + number + " does not exist.");
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
