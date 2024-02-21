package fredricksen.commands;

/**
 * Represents a "Help" Command, which extends the Command class.
 * A "Help" Command is a command that creates a HelpCommand object
 * with no parameters needed.
 */
public class HelpCommand extends Command {
    public HelpCommand() {}

    /**
     * It executes the Find command.
     * It will display the List of available commands that the user can type.
     *
     * @return A formatted String with all the available commands that the user can type.
     */
    @Override
    public String execute() {
        return "Below are the available commands and formats to follow!\n"
                + "1. To view all your current task: list\n"
                + "2. To print list of available commands: help\n"
                + "3. To find task that matches a keyWord: find <keyWord>\n"
                + "2. To add new task:\n"
                + "    a. todos: todo <todo task>\n"
                + "    b. deadlines: deadline <deadline task> /by <deadline with byTime>\n"
                + "    c. event: Event <event task> /from <startDate with startTiming> /to <endDate with endTiming>\n"
                + "3. To delete a task: delete <task number>\n"
                + "    a. delete <task number>\n"
                + "    b. delete all\n"
                + "4. To mark task as completed:"
                + "    a. mark <task number>\n"
                + "    b. mark all\n"
                + "5. To unmark completed task:"
                + "    a. unmark <task number>\n"
                + "    b. unmark all\n"
                + "6. To exit program: bye\n";
    }
}
