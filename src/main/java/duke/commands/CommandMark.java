package duke.commands;

import duke.Ui;
import duke.exceptions.DukeCeption;

import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * The command class when user wants to mark a task as done
 */
public class CommandMark extends Command {

    public CommandMark(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(String taskNumber) {
        try {
            Task task = taskList.getTask(taskNumber);
            task.markAsDone();
            ui.markTaskAsDone(task.toString());
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        }

    }
}
