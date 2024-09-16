package linus.commands;

import linus.Ui;
import linus.exceptions.LinusCeption;
import linus.tasks.Task;
import linus.tasks.TaskList;

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
        } catch (LinusCeption e) {
            ui.add(e.getMessage());
        }
    }
}
