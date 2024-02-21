package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.exception.CheckbotException;
import checkbot.task.TodoList;

/**
 * Represents a command to display the help message onto the GUI.
 */
public class HelpCommand extends Command {
    @Override
    public void executeCommand(TodoList todoList, Storage storage, Ui ui) throws CheckbotException {
        ui.displayHelp();
    }
}
