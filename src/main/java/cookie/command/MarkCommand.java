package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    int taskNum;

    /**
     * Constructs a MarkCommand with the specified input and task number.
     *
     * @param input    The input string associated with the command.
     * @param taskNum  The number of the task to mark as done.
     */
    public MarkCommand(String input, int taskNum) {
        super(input);
        this.taskNum = taskNum;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        Task[] taskArr = tasks.getTasks();
        int counter = tasks.getCounter();

        if (taskNum >= 1 && taskNum <= counter) {
            tasks.markTaskAsDone(taskNum);

            return ui.showMarkTaskDoneMessage(taskArr[taskNum - 1]);
        } else {
            throw new DukeException("UH OH! Invalid task number, please provide a valid task number!");
        }
    }
}
