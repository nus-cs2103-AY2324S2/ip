package cappy.command;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input)
            throws CappyException, IOException {
        ui.showExit();
    }
}
