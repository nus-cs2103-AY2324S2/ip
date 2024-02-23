package notduke.notdukeexception;

public class NotDukeInvalidCommand extends NotDukeException {
    private String command;
    public NotDukeInvalidCommand(String command) {
        this.command = command;
    }
    @Override
    public String toString() {
        return String.format("%s This is not a valid duke command: \"" + command + "\"", super.toString());
    }
}
