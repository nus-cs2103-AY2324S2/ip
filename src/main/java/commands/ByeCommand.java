package commands;

import exceptions.DukeException;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

/**
 * Encapsulates an exit command.
 */
public class ByeCommand extends Command {

    public static final String COMMAND = "bye";

    /**
     * Saves the file before safely exiting the program.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public void execute() throws DukeException, IOException {
        Storage storage = new Storage();
        storage.saveFile(tasks);
        Ui.exit();
    }
}
