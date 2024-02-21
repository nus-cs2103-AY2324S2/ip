package shon.command;

import javafx.application.Platform;
import shon.TaskList;

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
        Platform.exit();
        return "Bye";
    }
}
