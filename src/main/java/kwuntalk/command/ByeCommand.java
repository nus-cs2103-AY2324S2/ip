package kwuntalk.command;

import java.io.IOException;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;
import kwuntalk.exception.TasksFileException;


/**
 * Represents the command to exit the chatbot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     * @throws TasksFileException If tasks file can't be written to for saving of data into storage.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage) throws TasksFileException {
        try {
            ui.close();
            storage.saveTasksFile(taskList);
            return ui.showBye();
        } catch (IOException e) {
            throw new TasksFileException();
        }
    }


    /**
     * Returns true if it is a bye command.
     *
     * @return True for Bye commands.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
