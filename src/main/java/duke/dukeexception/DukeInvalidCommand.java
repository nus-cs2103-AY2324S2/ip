package duke.dukeexception;

public class DukeInvalidCommand extends DukeException {
    private String command;
    public DukeInvalidCommand(String command) {
        this.command = command;
    }
    @Override
    public String toString() {
        return String.format("%s This is not a valid duke command: \"" + command + "\"", super.toString());
    }
}
