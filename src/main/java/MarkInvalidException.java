public class MarkInvalidException extends DukeException {
    public MarkInvalidException(String command) {
        super("Please enter a valid integer after a " + command + " command.");
    }
}
