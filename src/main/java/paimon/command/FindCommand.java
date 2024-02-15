package paimon.command;

import paimon.ChatException;
import paimon.task.TaskList;
import paimon.util.UiHandler;


/**
 * Represents a command to search for tasks by a keyword. This command allows users
 * to find tasks that contain the specified keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword for searching tasks.
     *
     * @param keyword The keyword used to search through tasks' descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;

    }

    /**
     * Executes the find command by searching for tasks that contain the keyword in their description.
     * If matching tasks are found, their details are displayed to the user. If no tasks match the keyword,
     * a {@link ChatException} is thrown to indicate that no matching tasks were found.
     *
     * @param taskList The task list to search for matching tasks.
     * @return A String to be displayed.
     * @throws ChatException If no tasks containing the keyword are found.
     */
    public String execute(TaskList taskList) throws ChatException {
        String foundTasks = taskList.getFoundString(keyword);
        if (foundTasks.isEmpty()) {
            throw new ChatException("Could not find any tasks with that keyword");
        } else {
            return UiHandler.getFoundTasksMessage(foundTasks);
        }
    }

    /**
     * Indicates that executing this command does not signal the application to exit.
     *
     * @return false always, program is not exiting here
     */
    public boolean isExit() {
        return false;
    }

}
