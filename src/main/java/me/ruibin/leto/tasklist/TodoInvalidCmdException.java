package me.ruibin.leto.tasklist;

/** ** Exception for entering an invalid command for creating Todo tasks. */
public class TodoInvalidCmdException extends InvalidTaskException {
    /**
     * Exception thrown when a invalid <code>todo</code> command is entered.
     */
    public TodoInvalidCmdException() {
        super("todo command should be in the format:\n"
                + "  todo <description>\n"
                + "    Note: NO commas are allowed in the command");
    }
}
