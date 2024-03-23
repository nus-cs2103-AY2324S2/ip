package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    /**
     * Show all commands and their relevant syntax.
     * @param taskList TaskList containing tasks
     * @return String containing help message
     * @throws TimException
     */
    @Override
    public String execute(TaskList taskList) throws TimException {
        return GUI.showAvailCommands();
    }
}
