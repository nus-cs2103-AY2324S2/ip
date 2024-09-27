package alastor.command;

import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;

/**
 * Represents a command to show the list of commands.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
