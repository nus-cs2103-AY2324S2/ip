package exceptions;

public class TaskNotExistException extends DukeException {

    public TaskNotExistException(String number) {
        super("Task number " + number + " does not exist.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
