package exceptions;
public class InvalidIndexException extends DukeException{
    public InvalidIndexException() {
        super("Index out of bounds. Use list to view all tasks.");
    }
}
