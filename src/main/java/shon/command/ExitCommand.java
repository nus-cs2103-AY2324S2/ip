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
     */
    @Override
    public String execute(TaskList tasks) {
        return "";
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
