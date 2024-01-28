package duke.exceptions;

public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "________________________________________________\n"
                + super.getMessage() +
                "\n________________________________________________";
    }
}
