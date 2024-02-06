package duke;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskList Task list to delete from.
     * @param ui User interface to display message.
     * @param storage Storage where task content is stored.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.delete(this.index);
            setExit(false);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
