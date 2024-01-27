package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    boolean isExit();
}
