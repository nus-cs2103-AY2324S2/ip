package Thames.command;

import java.io.IOException;

import Thames.task.Task;
import Thames.Storage;
import Thames.TaskList;
import Thames.Ui;
import Thames.ThamesException;

/**
 * Subclass of Command that deals with adding tasks to task list.
 */
public class AddCommand extends Command {
    /** Task to be added to task list */
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to the given task list.
     * Notifies user of completion before saving new task list.
     *
     * @param tasks Task to be added.
     * @param ui User interface to notify user of completion.
     * @param storage Storage object that saves the new task list.
     * @throws ThamesException If error occurs while saving new task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException {
        try {
            assert storage != null: "Task list cannot be null!";
            assert tasks != null: "Ui cannot be null!";
            assert ui != null: "Storage cannot be null!";

            tasks.add(task);
            storage.save(tasks);
            return ui.addTask(task, tasks.size());
        } catch(IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddCommand) {
            return ((AddCommand) o).task.equals(this.task);
        }
        return false;
    }
}
