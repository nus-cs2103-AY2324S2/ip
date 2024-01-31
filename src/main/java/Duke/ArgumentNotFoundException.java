package duke;

public class ArgumentNotFoundException extends DukeException {
    public ArgumentNotFoundException(String command) {
        super("Arguments are required for " + command);
    }
}
