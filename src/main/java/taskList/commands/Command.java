package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

/**
 * Implementations of this interface provide specific functions for different commands.
 */
public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage);
}
