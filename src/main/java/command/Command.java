package command;

import exception.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {};
    public boolean isExit() { return false; }
}
