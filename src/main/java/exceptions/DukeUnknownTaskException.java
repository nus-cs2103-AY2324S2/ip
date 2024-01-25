package exceptions;

public class DukeUnknownTaskException extends DukeException {
    public DukeUnknownTaskException() {
        super("Sorry, but this task is unknown to me. Please try again using any of the following keywords: 'todo', 'deadline' or 'event'.");
    }
}
