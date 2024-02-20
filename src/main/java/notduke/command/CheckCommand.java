package notduke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import notduke.notdukeexception.NotDukeException;
import notduke.notdukeexception.NotDukeInvalidDateTimeFormat;
import notduke.storage.Storage;
import notduke.tasklist.TaskList;

public class CheckCommand extends Command {
    private String inputs;

    public CheckCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        try {
            LocalDate date = LocalDate.parse(inputs, DateTimeFormatter.ofPattern("d/M/yyyy"));
            return tasks.check(date);
        } catch (DateTimeParseException e1) {
            throw new NotDukeInvalidDateTimeFormat("d/M/yyyy");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
