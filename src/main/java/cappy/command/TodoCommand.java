package cappy.command;

import static cappy.constant.Message.MISSING_DESCRIPTION;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.task.Todo;
import cappy.ui.Ui;

import java.io.IOException;

/** Represents a command to add a new Todo task to the task list. */
public class TodoCommand extends Command {
    /**
     * Adds a new Todo task to the task list, and notify the user through the UI.
     *
     * @param tasks The task list that stores the tasks.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage for reading and writing task data.
     * @param input The parsed user input.
     * @throws CappyException If there is an application-specific exception during task execution.
     * @throws IOException If an I/O error occurs while interacting with the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input)
            throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException(MISSING_DESCRIPTION);
        }
        String description = String.join(" ", input.getPositionalArguments());
        Todo task = new Todo(description);
        tasks.addTask(task);
        ui.showAddedTask(task, tasks);
        tasks.save();
    }
}
