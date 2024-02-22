package commands;

import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        return ui.showInstructions();
    }
}
