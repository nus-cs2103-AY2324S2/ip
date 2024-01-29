/*
 * MarkCommand.java
 * This class represents a command to mark a task as done in the Duke application.
 * It allows the user to mark a task as completed by specifying its index in the task list.
 */

package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;
import duke.task.Storage;
import duke.task.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    private final String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            int index = Integer.parseInt(fullCommand.substring(5).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("duke.Task number " + (index + 1) + " does not exist.");
            }
            Task task = tasks.getTasks().get(index);
            ui.showMarkedMessage(task);
            task.markAsDone();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number to unmark.");
        }
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
