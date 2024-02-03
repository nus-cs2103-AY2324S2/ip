package leto.tasklist;

public class EventInvalidCmdException extends InvalidTaskException {
    public EventInvalidCmdException() {
        super("event command should be in the format:\n"
                + "  event <description> /from <date> /to <date>\n"
                + "    <date> should be in the format YYYY-MM-DD.\n"
                + "    Note: NO commas are allowed in the command");
    }
}
