package alpa.commands;

import alpa.utils.Storage;
import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.exceptions.AlpaException;

public interface Command {
    void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException;
    boolean isExit();
}
