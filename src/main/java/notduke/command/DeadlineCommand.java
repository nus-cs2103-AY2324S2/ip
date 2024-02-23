package notduke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import notduke.notdukeexception.NotDukeException;
import notduke.notdukeexception.NotDukeInvalidDateTimeFormat;
import notduke.notdukeexception.NotDukeMissingArgument;
import notduke.storage.Storage;
import notduke.task.Deadlines;
import notduke.tasklist.TaskList;

public class DeadlineCommand extends Command {
    private String inputs;

    public DeadlineCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        try {
            String[] values = inputs.split(" /by ", 2);
            LocalDateTime by = LocalDateTime.parse(values[1], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return tasks.add(new Deadlines(values[0], by, false));
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new NotDukeMissingArgument("deadline");
        } catch (DateTimeParseException e2) {
            throw new NotDukeInvalidDateTimeFormat("d/M/yyyy HHmm");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
