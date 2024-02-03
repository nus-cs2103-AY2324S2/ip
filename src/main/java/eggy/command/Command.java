package eggy.command;

import eggy.exception.EggyException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException;

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    public boolean isExit() {
        return false;
    }
}
