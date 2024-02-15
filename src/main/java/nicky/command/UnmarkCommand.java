package nicky.command;

import java.io.IOException;

import nicky.NickyException;
import nicky.Ui;
import nicky.task.Storage;
import nicky.task.Task;
import nicky.task.TaskList;

/**
 * Represents a command to unmark a task as not done in the Nicky application.
 * It allows the user to mark a completed task as not done by specifying its index in the task list.
 */
public class UnmarkCommand extends Command {
    private final String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NickyException, IOException {
        String response;
        try {
            int index = Integer.parseInt(fullCommand.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new NickyException("Task number " + (index + 1) + " does not exist.");
            }
            Task task = tasks.getTasks().get(index);
            response = ui.showUnmarkedMessage(task);
            task.markAsNotDone();
        } catch (NumberFormatException e) {
            throw new NickyException("Please enter a valid task number to unmark.");
        }
        storage.saveTasks(tasks);
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
