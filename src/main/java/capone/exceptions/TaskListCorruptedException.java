package capone.exceptions;

import capone.Capone;

public class TaskListCorruptedException extends CaponeException {
    public TaskListCorruptedException(String e) {
        super(e);
    }
}
