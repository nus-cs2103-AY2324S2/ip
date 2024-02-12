package aaron.exception;

/**
 * class that represents an exception when a user enters a command to enter
 * a task with no name/descrption into the tasklist of aaronbot
 */
public class TaskNoNameException extends TaskErrorException {
    public TaskNoNameException(String e) {
        super(e);
    }
}
