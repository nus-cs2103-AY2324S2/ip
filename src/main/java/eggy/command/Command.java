package eggy.command;

import eggy.exception.DateTimeFormatException;
import eggy.exception.EggyException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException;

    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeFormatException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    public boolean isExit() {
        return false;
    }
}
