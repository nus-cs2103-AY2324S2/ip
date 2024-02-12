/*
 * UnmarkCommand.java
 * This class represents a command to unmark a task as not done in the Duke application.
 * It allows the user to mark a completed task as not done by specifying its index in the task list.
 */

package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Ui;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to unmark a task as not done in the Duke application.
 */
public class UnmarkCommand extends Command {
    private final String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String response;
        try {
            int index = Integer.parseInt(fullCommand.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("duke.Task number " + (index + 1) + " does not exist.");
            }
            Task task = tasks.getTasks().get(index);
            response = ui.showUnmarkedMessage(task);
            task.markAsNotDone();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number to unmark.");
        }
        storage.saveTasks(tasks);
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
