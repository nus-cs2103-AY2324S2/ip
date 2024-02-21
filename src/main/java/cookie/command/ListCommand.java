package cookie.command;

import cookie.CookieException;
import cookie.task.Task;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified input.
     *
     * @param input The input string associated with the command.
     */
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws CookieException {
        Task[] taskArr = tasks.getTasks();
        int counter = tasks.getCounter();
        if (counter == 0) {
            throw new CookieException("UH OH! You have not added any tasks!");
        }
        return ui.showTaskList(taskArr, counter);
    }
}
