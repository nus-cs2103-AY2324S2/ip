package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents the command used to exit the application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public void execute(TaskList tasks, Storage storage, Ui ui) {}
}
