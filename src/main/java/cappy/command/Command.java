package cappy.command;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;

/**
 * Abstract class representing a command in the Cappy application.
 *
 * <p>The {@code Command} class serves as the base class for all specific command implementations.
 * It declares an abstract method {@code execute} that must be implemented by concrete subclasses.
 * Each concrete command class encapsulates a specific operation that can be performed.
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list, user interface, storage, and parsed input.
     *
     * @param tasks The task list that stores the tasks.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage for reading and writing task data.
     * @param input The parsed user input.
     * @throws CappyException If there is an application-specific exception during task execution.
     * @throws IOException If an I/O error occurs while interacting with the storage.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input)
            throws CappyException, IOException;
}
