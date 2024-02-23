package ficin;

import ficin.task.TaskList;

/**
 * The HelpCommand class represents a command to display help to the user.
 * It extends the Command class and implements the execute method.
 */
public class HelpCommand extends Command {

    /**
     * Executes to display help to the user.
     *
     * @param taskList  The task list (not used in this command).
     * @param ui        The user interface to interact with the user.
     * @param userInput The user input (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) {
        ui.showHelp();
    }
}
