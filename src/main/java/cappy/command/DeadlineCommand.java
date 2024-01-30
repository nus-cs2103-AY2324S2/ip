package cappy.command;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.parser.Parser;
import cappy.storage.Storage;
import cappy.task.Deadline;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    /**
     * Adds a new Deadline task to the task list, and notify the user through the UI.
     *
     * @param tasks   The task list that stores the tasks.
     * @param ui      The user interface for interaction with the user.
     * @param storage The storage for reading and writing task data.
     * @param input   The parsed user input.
     * @throws CappyException If there is an application-specific exception during task execution.
     * @throws IOException    If an I/O error occurs while interacting with the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input)
            throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException("Please enter the task description.");
        } else if (!input.hasNamedArgument("by")) {
            throw new CappyException(
                    "Please specify the due date of the deadline task using /by [Date Time].");
        }
        String description = String.join(" ", input.getPositionalArguments());
        String dueString = input.getNamedArgument("by");
        try {
            LocalDateTime due = Parser.parseDateTime(dueString);
            Deadline task = new Deadline(description, due);
            tasks.addTask(task);
            ui.showAddedTask(task, tasks);
            tasks.save();
        } catch (DateTimeParseException e) {
            throw new CappyException("Please use the correct datetime format.");
        }
    }
}
