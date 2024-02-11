package duke;

public class IndexOutOfRangeException extends DukeException {
    public IndexOutOfRangeException(int idx, int size) {
        super(idx + " is not valid! It must be between 1 and the number of tasks in the list. There are " + size + " tasks.");
    }
}
