package shon.command;

import javafx.application.Platform;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Exits the chatbot.
     *
     */
    @Override
    public String execute() {
        Platform.exit();
        return "Bye";
    }
}
