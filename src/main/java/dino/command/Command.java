package dino.command;

import java.io.IOException;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;

/**
 * The abstract class representing a command in the Dino task management application.
 */
public abstract class Command {

    /**
     * Executes the command with the given TaskList and Storage.
     *
     * @param taskList The TaskList containing the tasks.
     * @param storage The Storage used to save/load tasks.
     * @return A message indicating the result of the command execution.
     * @throws IOException If an I/O error occurs while accessing the storage.
     * @throws DinoException If an error specific to the Dino application occurs.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws IOException, DinoException;

}
