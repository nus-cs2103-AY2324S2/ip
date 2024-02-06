package commands;

import exceptions.LeluException;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException;
}
