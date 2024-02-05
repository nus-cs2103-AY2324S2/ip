package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage);
}
