package mike.command;

import mike.MikeException;
import mike.TaskList;
import mike.Ui;
import mike.command.Command;
import mike.task.Task;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        super("");
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList) throws MikeException {
        int taskIndex = taskNumber - 1;
        if (taskList.isEmpty()) {
            throw new MikeException("There are no tasks to mark. Please add a task first.");
        } else if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new MikeException("That's suspicious. Please enter a number in the range 1-" + taskList.size() + ".");
        }
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:\n  " + task;
        Ui.display(message);
        /*
           TODO:
            1. Check that task is or is not done.
        */
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
