package cappy.command;

import java.io.IOException;

import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.error.CappyException;

public abstract class Command {
    abstract public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws CappyException, IOException;
}

