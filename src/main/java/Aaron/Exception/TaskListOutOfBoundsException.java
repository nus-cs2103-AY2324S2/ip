package aaron.exception;

/**
 * class that represents an exception when the user inputs an index out of
 * bounds
 * of the tasklist of aaronbot to be deleted/marked/unmarked
 */
public class TaskListOutOfBoundsException extends IndexErrorException {
    public TaskListOutOfBoundsException(String e) {
        super(e);
    }

}
