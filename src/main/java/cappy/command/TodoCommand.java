package cappy.command;

import java.io.IOException;

import cappy.task.Todo;
import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.error.CappyException;

public class TodoCommand extends Command {
    /**
     * Adds a new Todo task to the task list, and notify the user through the UI.
     *
     * @param tasks   The task list that stores the tasks.
     * @param ui      The user interface for interaction with the user.
     * @param storage The storage for reading and writing task data.
     * @param input   The parsed user input.
     * @throws CappyException If there is an application-specific exception during task execution.
     * @throws IOException    If an I/O error occurs while interacting with the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException("Please enter the task description.");
        }
        String description = input.getPositionalArgument(0);
        Todo task = new Todo(description);
        tasks.addTask(task);
        ui.showAddedTask(task, tasks);
        tasks.save();
    }
}
