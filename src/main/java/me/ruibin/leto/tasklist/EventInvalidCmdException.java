package me.ruibin.leto.tasklist;

/** Exception for entering an invalid command for creating Event tasks. */
public class EventInvalidCmdException extends InvalidTaskException {
    /** Further help message specifying how to correctly enter a command for Event. */
    public EventInvalidCmdException() {
        super("event command should be in the format:\n"
                + "  event <description> /from <date> /to <date>\n"
                + "    <date> should be in the format YYYY-MM-DD.\n"
                + "    Note: NO commas are allowed in the command");
    }
}
