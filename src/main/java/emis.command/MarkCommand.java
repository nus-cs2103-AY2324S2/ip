package main.java.emis.command;
import main.java.emis.exceptions.EmisException;
import main.java.emis.*;

/**
 * The MarkCommand class represents a command to mark a task as done in the EMIS application.
 * When executed, it marks the specified task as done and updates the storage.
 */
public class MarkCommand extends Command {
    /** The index of the task to mark as done. */
    private int taskNo;

    /**
     * Constructs a new MarkCommand object with the specified task index.
     *
     * @param taskNo The index of the task to mark as done.
     */
    public MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the mark command by marking the specified task as done and updating the storage.
     *
     * @param t The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param s The Storage object handling loading and saving of tasks.
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        try {
            t.markAsDone(this.taskNo);
            s.updateStorage();
        } catch (EmisException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as the mark command does not represent an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
