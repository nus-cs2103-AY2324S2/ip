package duke;

/**
 * Represents the command to list task content.
 */
public class ListCommand extends Command {
    public ListCommand() {

    }

    /**
     * Executes the listing of task content from the given TaskList.
     *
     * @param taskList List of tasks to display.
     * @param ui User interface.
     * @param storage Storage to store Tasklist content.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
        setExit(false);
    }
}
