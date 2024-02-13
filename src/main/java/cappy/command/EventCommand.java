package cappy.command;

import static cappy.constant.Message.INVALID_DATETIME_FORMAT;
import static cappy.constant.Message.MISSING_DESCRIPTION;
import static cappy.constant.Message.MISSING_TO_FROM_DATETIME;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.parser.Parser;
import cappy.storage.Storage;
import cappy.task.Event;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/** Represents a command to add an Event task to the task list. */
public class EventCommand extends Command {
    private static final String PREFIX_FROM = "from";
    private static final String PREFIX_TO = "to";

    /**
     * Adds a new Event task to the task list, and notify the user through the UI.
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
        } else if (!input.hasNamedArgument(PREFIX_FROM) || !input.hasNamedArgument(PREFIX_TO)) {
            throw new CappyException(MISSING_TO_FROM_DATETIME);
        }
        String description = String.join(" ", input.getPositionalArguments());
        String fromString = input.getNamedArgument(PREFIX_FROM);
        String toString = input.getNamedArgument(PREFIX_TO);
        try {
            LocalDateTime from = Parser.parseDateTime(fromString);
            LocalDateTime to = Parser.parseDateTime(toString);
            Event task = new Event(description, from, to);
            tasks.addTask(task);
            ui.showAddedTask(task, tasks);
            tasks.save();
        } catch (DateTimeParseException e) {
            throw new CappyException(INVALID_DATETIME_FORMAT);
        }
    }
}
