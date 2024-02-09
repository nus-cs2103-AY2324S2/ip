package duke.command;

/**
 * Represents a command that is not recognized by the application.
 */
public class UnknownCommand extends Command {
    private final String command;

    public UnknownCommand(String command) {
        this.command = command;
    }

    public UnknownCommand() {
        this.command = "";
    }

    @Override
    public String execute() {
        return String.format("I'm sorry, but I don't know what \"%s\" means.\n", this.command);
    }

    @Override
    public String explain() {
        return "Displays an error message for an unrecognized command.";
    }
}
