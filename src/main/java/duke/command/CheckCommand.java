package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeInvalidDateTimeFormat;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class CheckCommand extends Command {
    private String inputs;

    public CheckCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(inputs, DateTimeFormatter.ofPattern("d/M/yyyy"));
            return tasks.check(date);
        } catch (DateTimeParseException e1) {
            throw new DukeInvalidDateTimeFormat("d/M/yyyy");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
