package eggy.command;

import eggy.exception.DateTimeFormatException;
import eggy.exception.EggyException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list of the chat bot.
     * @param ui      The user interface of the chat bot.
     * @param storage The storage of the chat bot.
     * @throws EggyException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException;

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTime The date and time string to be parsed.
     * @return The LocalDateTime object parsed from the date and time string.
     * @throws DateTimeFormatException If the date and time string is in an invalid format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeFormatException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return Whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
