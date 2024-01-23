package duke;

public class DukeException extends Exception {
    public DukeException(String exception) {
        super(
        "____________________________________________________________\n" +
        "   OOPS!!! " + exception + "\n" +
        "____________________________________________________________\n"
        );
    }
}
