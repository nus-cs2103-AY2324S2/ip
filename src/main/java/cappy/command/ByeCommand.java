package cappy.command;

import java.io.IOException;

import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.error.CappyException;

public class ByeCommand extends Command {
    /**
     * Shows an exit message in the user interface.
     *
     * @param tasks   The task list that stores the tasks.
     * @param ui      The user interface for interaction with the user.
     * @param storage The storage for reading and writing task data.
     * @param input   The parsed user input.
     * @throws CappyException If there is an application-specific exception during task execution.
     * @throws IOException    If an I/O error occurs while interacting with the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws CappyException, IOException {
        ui.showExit();
    }
}

