package nicky.command;

import java.io.IOException;

import nicky.NickyException;
import nicky.Ui;
import nicky.task.Storage;
import nicky.task.Task;
import nicky.task.TaskList;

/**
 * Represents a command to delete a task from the Nicky application.
 * It allows the user to remove a task by specifying its index in the task list.
 */
public class DeleteCommand extends Command {
    private final String fullCommand;

    public DeleteCommand(String fullCommand) {
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
            Task removedTask = tasks.removeTask(index);
            response = ui.showDeletedMessage(tasks, removedTask);
        } catch (NumberFormatException e) {
            throw new NickyException("Please enter a valid task number to delete.");
        }
        storage.saveTasks(tasks);
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
