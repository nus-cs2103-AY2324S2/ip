package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;

/**
 * This command handles the exiting of the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand} with the specified TaskList, Ui, and Storage.
     * This command is responsible for exiting the application.
     *
     * @param tasks   The TaskList to be saved upon exit.
     * @param ui      The Ui instance for user interaction.
     * @param storage The Storage instance for saving the TaskList.
     */
    public ExitCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    /**
     * Executes the exit command. It will display a goodbye message, save the current state
     * of tasks, and then terminate the application.
     */
    @Override
    public String execute() {
        String str = ui.showGoodbye();
        storage.save(tasks);
        return str;
    }
}
