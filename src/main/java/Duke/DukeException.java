package duke;

public class DukeException extends Exception {
    public DukeException(String eString) {
        super("Exception: " + eString);
    }
}
