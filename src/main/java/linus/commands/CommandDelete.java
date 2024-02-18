package linus.commands;

import linus.Ui;
import linus.exceptions.LinusCeption;
import linus.tasks.Task;
import linus.tasks.TaskList;

/**
 * The command class when user inputs delete
 */
public class CommandDelete extends Command {

    public CommandDelete(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(String taskNumber) {
        try {
            Task task = taskList.delete(taskNumber);
            ui.deleteTask(task.toString());
        } catch (LinusCeption e) {
            ui.add(e.getMessage());
        }
    }
}
