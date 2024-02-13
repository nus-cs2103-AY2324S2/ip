package duke.exceptions;

public class InvalidIndexException extends DukeException{
    public InvalidIndexException() {
        super("Index out of bounds. Use list to view all duke.tasks.");
    }
}
