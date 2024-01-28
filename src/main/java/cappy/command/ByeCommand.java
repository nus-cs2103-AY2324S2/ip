package cappy.command;

import java.io.IOException;

import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.error.CappyException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws CappyException, IOException {
        ui.showExit();
    }
}

