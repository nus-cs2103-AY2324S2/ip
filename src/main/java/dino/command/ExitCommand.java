package dino.command;

import java.io.IOException;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;
import javafx.application.Platform;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, DinoException {
        try {
            storage.saveTasksToFile(taskList.getTaskList());
            return "Bye";
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        } finally {
            Platform.exit();
        }
    }
}
