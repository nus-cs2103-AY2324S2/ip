package duke.command;

/**
 * Represents a command to display the list of commands that the application understands.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public final String input;

    public HelpCommand(String input) {
        this.input = input;
    }

    public HelpCommand() {
        this.input = "";
    }

    @Override
    public String execute() {
        if (this.input == null || this.input.isEmpty()) {
            return this.explain();
        }

        Command command = Command.interpretCommand(this.input);
        return command.explain();
    }

    @Override
    public String explain() {
        return "Displays the list of commands that the application understands."
                + "Format: help [command]\n"
                + "Example: help list";
    }

}
