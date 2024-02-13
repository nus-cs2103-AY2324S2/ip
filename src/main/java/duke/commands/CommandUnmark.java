package duke.commands;

import duke.Ui;
import duke.exceptions.DukeCeption;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class when user wants to mark a task as not done
 */
public class CommandUnmark extends Command {

    public CommandUnmark(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(String taskNumber) {
        try {
            Task task = taskList.getTask(taskNumber);
            task.markAsNotDone();
            ui.markTaskAsNotDone(task.toString());
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        }
    }
}
