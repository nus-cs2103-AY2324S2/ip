package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.Task;
import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Adds the specified task to the list of tasks.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Constructor for the AddCommand class.
     * @param newTask The task to be added.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        int listCount = tasks.getTaskListSize();
        return ui.showAddTaskResponse(newTask, listCount);
    }
}
