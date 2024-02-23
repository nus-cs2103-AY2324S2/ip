package teletubbi;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        setExit(false);
        String helpMessage =
                "teletubbi commands\n\n" +
                "deadline <description> /by <deadline>\n" +
                "todo <description>\n" +
                "event <description> /from <start> /to <end>\n" +
                "list\n" +
                "mark <index>\n" +
                "unmark <index>\n" +
                "delete <index>\n" +
                "find <keyword>";
        return helpMessage;
    }
}
