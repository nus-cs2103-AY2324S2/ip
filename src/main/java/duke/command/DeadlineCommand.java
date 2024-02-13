package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeInvalidDateTimeFormat;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.task.Deadlines;
import duke.tasklist.TaskList;

public class DeadlineCommand extends Command {
    private String inputs;

    public DeadlineCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] values = inputs.split(" /by ", 2);
            LocalDateTime by = LocalDateTime.parse(values[1], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return tasks.add(new Deadlines(values[0], by, false));
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new DukeMissingArgument("deadline");
        } catch (DateTimeParseException e2) {
            throw new DukeInvalidDateTimeFormat("d/M/yyyy HHmm");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
