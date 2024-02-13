package duke.commands;

import duke.Ui;

import duke.exceptions.DukeCeption;

import duke.tasks.Task;
import duke.tasks.TaskList;

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
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        }
    }
}
