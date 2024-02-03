package duke.command;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!\n";
    }

    @Override
    public boolean terminate() {
        return true;
    }
}
