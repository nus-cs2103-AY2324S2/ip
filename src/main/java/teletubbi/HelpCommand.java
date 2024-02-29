package teletubbi;

/**
 * Represents the command to get teletubbi help guide.
 */
public class HelpCommand extends Command {

    /**
     * Shows the teletubbi help guide.
     *
     * @param taskList Tasklist containing tasks.
     * @param ui User interface.
     * @param storage Storage to store task content.
     * @return Teletubbi help guide.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        setExit(false);
        String helpMessage =
                "teletubbi commands\n\n"
                + "deadline <description> /by <deadline>\n"
                + "todo <description>\n"
                + "event <description> /from <start> /to <end>\n"
                + "list\n"
                + "mark <index>\n"
                + "unmark <index>\n"
                + "delete <index>\n"
                + "find <keyword>";
        return helpMessage;
    }
}
