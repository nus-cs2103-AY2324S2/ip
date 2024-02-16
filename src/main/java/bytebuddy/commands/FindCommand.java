package bytebuddy.commands;

import static bytebuddy.parser.Parser.splitStringWithTrim;

import java.util.List;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to find tasks in the task list that match a specified keyword.
 * The keyword is used to search for tasks containing the specified text in their descriptions.
 * Executes the 'findInTaskList' method in the provided 'TaskList' instance.
 */
public class FindCommand implements Command {

    private List<String> keywords;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param info The keyword(s) or text to search for among all the tasks in the task list.
     */
    public FindCommand(String info) {
        this.keywords = splitStringWithTrim(info, ",", 0);
    }

    /**
     * Executes the 'findInTaskList' method in the provided 'TaskList' instance to find tasks
     * containing the specified keyword in their descriptions.
     *
     * @param taskList The TaskList instance containing the list of tasks.
     * @param ui       The Ui instance for user interaction.
     * @param storage  The Storage instance for file storage operations.
     * @throws ByteBuddyException If there is an issue executing the 'findInTaskList' method.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        return "\t " + taskList.findTaskWithKeywordInTaskList(keywords);
    }

    /**
     * Checks if the DeleteCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as FindCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
