package commands;

import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.MainWindow;
import ui.Ui;


/**
 * This class represents a command for ending the conversation with the chatbot.
 * When executed, this command terminates the chat session and closes the chatbot
 * interface, allowing the user to exit gracefully.
 */
 public class ByeCommand extends Command {

    /**
     * Executes the command to exit the application.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        storage.save(tasks);
        MainWindow.canExit = true;
        return ui.exit();
    }
}
