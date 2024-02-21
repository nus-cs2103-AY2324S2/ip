package cookie.command;

import cookie.CookieException;
import cookie.task.Task;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int taskNum;

    /**
     * Constructs a DeleteCommand with the specified input and task number.
     *
     * @param input   The input string associated with the command.
     * @param taskNum The number of the task to be deleted.
     */
    public DeleteCommand(String input, int taskNum) {
        super(input);
        this.taskNum = taskNum;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws CookieException {
        Task[] taskArr = tasks.getTasks();
        int counter = tasks.getCounter();

        if (taskNum >= 1 && taskNum <= counter) {
            String deleteMsg = ui.showRemoveTaskMessage(taskArr[taskNum - 1], counter);
            tasks.deleteTask(taskNum);

            return deleteMsg;
        } else {
            throw new CookieException("UH OH! Invalid task number, please provide a valid task number!");
        }
    }
}
