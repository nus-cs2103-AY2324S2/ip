package commands;

/**
 * The Help class represents a command to display help information.
 * It implements the Command interface and specifies the execution behavior for displaying help content.
 */
public class Help implements Command {

    @Override
    public String execute() {
        /**
         * Executes the Help command by displaying help information retrieved from the specified file path.
         */
        String help = "list: Lists all tasks\n" +
                "find: Finds task with matching name\n" +
                "todo: Creates todo\n" +
                "event: Creates event\n" +
                "deadline: Creates deadline\n" +
                "mark: Marks task as done\n" +
                "unmark: Marks task as not done\n" +
                "delete: Deletes task, use `0` to clear\n" +
                "bye: Ends program";

        return help;
    }
}

