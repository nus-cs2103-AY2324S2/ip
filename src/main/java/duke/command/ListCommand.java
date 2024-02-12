package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        Task[] taskArr = tasks.getTasks();
        int counter = tasks.getCounter();
        return ui.showTaskList(taskArr, counter);
    }
}
