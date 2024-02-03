package duke.exceptions;

public abstract class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(String.format("OOPS!!! %s", errorMessage));
    }
}
