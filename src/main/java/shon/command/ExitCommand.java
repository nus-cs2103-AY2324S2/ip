package shon.command;

import shon.TaskList;
import shon.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Exits the chatbot.
     *
     * @param tasks The <code>TaskList</code> is not used in this command.
     * @param ui The <code>Ui</code> used to output the result of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.exit();
    }

    /**
     * Indicates to the chatbot to exit using a true value.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
