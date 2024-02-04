package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException;

    public boolean isExit() {
        return false;
    }
}