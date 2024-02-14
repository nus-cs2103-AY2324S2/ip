package commands;

import exceptions.DukeException;
import storage.Storage;

import ui.Ui;

import java.io.IOException;

/**
 * Encapsulates an exit command.
 */
public class ByeCommand extends  Command {

    public static final String COMMAND = "bye";

    @Override
    public String executeCommand() throws IOException, DukeException {
        Storage storage = new Storage();
        storage.saveFile(taskList);

        return Ui.exit();
    }
}
