package main.java.emis.command;
import main.java.emis.exceptions.EmisException;
import main.java.emis.*;

/**
 * The DeleteCommand class represents a command to delete a task in the EMIS application.
 * When executed, it deletes the task with the specified task number from the task list.
 */
public class DeleteCommand extends Command {
    /** The task number of the task to be deleted. */
    private int taskNo;

    /**
     * Constructs a new DeleteCommand object with the specified task number.
     *
     * @param taskNo The task number of the task to be deleted.
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the delete command by deleting the task with the specified task number from the task list and updating the storage.
     *
     * @param t The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param s The Storage object handling loading and saving of tasks.
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        try {
            t.deleteTask(this.taskNo);
            s.updateStorage();
        } catch (EmisException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as the delete command does not represent an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
