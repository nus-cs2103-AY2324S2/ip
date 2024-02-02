package Duke;

/**
 * The ListCommand class represents a command to list all tasks.
 * It implements the Command interface.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, displaying the list of all tasks.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for displaying user interface messages.
     * @param storage The Storage object (not used in list command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
