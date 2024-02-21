package commands;

import exceptions.HowieException;
import storage.Storage;

import ui.Ui;

import java.io.IOException;

/**
 * Encapsulates an exit command.
 */
public class ByeCommand extends  Command {

    public static final String COMMAND = "bye";

    @Override
    public String executeCommand() throws IOException, HowieException {
        Storage storage = new Storage();
        storage.saveFile(taskList);

        return Ui.exit();
    }
}
