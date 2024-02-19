package command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;
import task.Task;

/**
 * Represents a command to add notes to an existing task in the task list.
 * This command is typically used to add additional information or comments to a task.
 */
public class AddNotesCommand extends Command {

    private final int index;
    private final String notes;

    /**
     * Constructs a new AddNotesCommand with the specified task index and notes.
     *
     * @param index The index of the task in the task list to which notes should be added.
     * @param notes The notes to be added to the task.
     */
    public AddNotesCommand(int index, String notes) {
        this.index = index;
        this.notes = notes;
    }

    /**
     * Executes the AddNotesCommand by adding the specified notes to the task at the given index.
     *
     * @param tasks The task list containing the task to which notes should be added.
     * @param ui The user interface for displaying messages.
     * @param storage The storage for saving the updated task list to disk.
     * @return A message indicating that the notes have been successfully added to the task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.index - 1);
        task.addNotes(this.notes);
        super.serializeArrayList(tasks.getList(), storage.getFilePath());
        return "Added notes to task";
    }
}
