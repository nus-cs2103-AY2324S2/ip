package duke.exceptions;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Invalid task. Use help for guidance.");
    }
}
