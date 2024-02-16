package nicky.command;

import java.io.IOException;

import nicky.NickyException;
import nicky.Ui;
import nicky.task.Storage;
import nicky.task.Task;
import nicky.task.TaskList;

/**
 * Represents a command to mark a task as done in the Nicky application.
 * It allows the user to mark a task as completed by specifying its index in the task list.
 */
public class MarkCommand extends Command {
    private final String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NickyException, IOException {
        String response;
        try {
            int index = Integer.parseInt(fullCommand.substring(5).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new NickyException("Task number " + (index + 1) + " does not exist.");
            }
            Task task = tasks.getTasks().get(index);
            response = ui.showMarkedMessage(task);
            task.markAsDone();
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
