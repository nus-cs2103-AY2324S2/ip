package taskList.commands;

import taskList.Storage;
import taskList.TaskList;
import taskList.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage);
}
