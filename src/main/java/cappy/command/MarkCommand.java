package cappy.command;

import static cappy.constant.Message.INVALID_INDEX;
import static cappy.constant.Message.MISSING_INDEX;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;

/** Represents a command to mark a task as complete. */
public class MarkCommand extends Command {
    /**
     * Marks the task at the given index from the task list as completed, and notify the user
     * through the UI. The index is retrieved from the parsed user input.
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
            throw new CappyException(MISSING_INDEX);
        }
        String indexStr = input.getPositionalArgument(0);
        try {
            int index = Integer.parseInt(indexStr);
            if (!tasks.validIndex(index)) {
                throw new CappyException(INVALID_INDEX);
            }
            tasks.getTask(index).done();
            String[] messages = {
                "Nice! I've marked this task as done:", tasks.getTask(index).toString()
            };
            ui.show(messages);
            tasks.save();
        } catch (NumberFormatException e) {
            throw new CappyException(INVALID_INDEX);
        }
    }
}
