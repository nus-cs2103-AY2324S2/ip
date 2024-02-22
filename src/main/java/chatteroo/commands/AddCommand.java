package chatteroo.commands;

import chatteroo.ChatterooException;
import chatteroo.tasks.Task;
import chatteroo.tasks.TaskList;
import chatteroo.ui.Ui;
import chatteroo.storage.Storage;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatterooException {
        tasks.addTask(newTask);
        int listCount = tasks.getTaskListSize();
        return ui.showAddTaskResponse(newTask, listCount);
    }
}
