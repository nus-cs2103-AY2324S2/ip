package shuheng.exceptions;

/**
 * This class represents an error caused by accessing a invalid index in the task list.
 */
public class HistoryIndexException extends DukeException {
    public HistoryIndexException() {
        super("That's not a valid index you can choose! Check the possible index with /list!");
    }
}
