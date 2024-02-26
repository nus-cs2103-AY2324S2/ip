package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

/**
 * Represents commands that displays the active list of tasks.
 */
public class ListCommand extends Command {
    private boolean isExit = false;
    /**
     * Instantiates new ListCommand instance.
     */
    public ListCommand() {
        super(false);
    }
    /**
     * {@inheritDoc}
     * @param tasks TaskList of active tasks
     * @param ui Ui instance to print responses.
     * @param storage Storage instance to store any data.
     * @throws InvalidCommandException If command is of invalid format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        return ui.printList(tasks);
    }
}
