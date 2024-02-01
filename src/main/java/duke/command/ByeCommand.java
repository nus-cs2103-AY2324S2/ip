package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.TasksFileException;
import duke.Ui;

import java.io.IOException;


/**
 * Represents the command to exit the chatbot.
 */
public class ByeCommand extends Command {


    /**
     * Executes the bye command.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @throws TasksFileException If tasks file can't be written to for saving of data into storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasksFileException {
        try {
            ui.close();
            storage.saveTasksFile(taskList);
            ui.goodbye();
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
