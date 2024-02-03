package Duke.Command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import Duke.Exception.InvalidArgumentException;
import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

public abstract class Command {
    boolean isActive = true;
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException;

    public boolean getIsActive() {
        return this.isActive;
    }
}
