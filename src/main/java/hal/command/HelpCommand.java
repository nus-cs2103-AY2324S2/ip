package hal.command;

import hal.task.TaskList;

/**
 * The HelpCommand class represents a command to display help information.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new HelpCommand object.
     */
    public HelpCommand() {
    }

    /**
     * Executes the help command.
     *
     * @param taskList The TaskList containing the tasks (unused in this command).
     * @return A message containing the list of available commands.
     */
    @Override
    public String execute(TaskList taskList) {
        return "todo <task description> \n" +
                "deadline <task description> /by YYYY-MM-DD\n" +
                "event <task description> /from YYYY-MM-DD /to YYYY-MM-DD\n" +
                "list\n" +
                "mark <task number>\n" +
                "unmark <task number>\n" +
                "delete <task number>\n" +
                "find <keyword>\n" +
                "bye";
    }
}
