package cappy.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import cappy.task.Event;
import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.parser.Parser;
import cappy.error.CappyException;

public class EventCommand extends Command {
    /**
     * Adds a new Event task to the task list, and notify the user through the UI.
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
        } else if (!input.hasNamedArgument("from") || !input.hasNamedArgument("to")) {
            throw new CappyException("Please specify the duration of the event using /from [Date Time] /to [Date Time].");
        }
        String description = input.getPositionalArgument(0);
        String fromString = input.getNamedArgument("from");
        String toString = input.getNamedArgument("to");
        try {
            LocalDateTime from = Parser.parseDateTime(fromString);
            LocalDateTime to = Parser.parseDateTime(toString);
            Event task = new Event(description, from, to);
            tasks.addTask(task);
            ui.showAddedTask(task, tasks);
            tasks.save();
        } catch (DateTimeParseException e) {
            throw new CappyException("Please use the correct datetime format.");
        }
    }
}
