package exceptions;

/**
 * Custom exception class representing the scenario where a task is empty.
 * Extends the DukeExceptions class.
 */
public class EmptyTaskException extends DukeExceptions {
    private String msg = "Name of task cannot be empty!";
    public EmptyTaskException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
