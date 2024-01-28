package bytebuddy.commands;

import bytebuddy.exceptions.DukeException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    boolean isExit();
}
