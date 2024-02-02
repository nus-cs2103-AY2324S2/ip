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
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private String inputs;

    public DeadlineCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String timeFormat = "d/M/yyyy HHmm";
        try {
            String[] values = inputs.split(" /by ", 2);
            LocalDateTime by = LocalDateTime.parse(values[1], DateTimeFormatter.ofPattern(timeFormat));
            ui.sendReply(tasks.add(new Deadlines(values[0], by, false)));
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new DukeMissingArgument(2,"deadline");
        } catch (DateTimeParseException e2) {
            throw new DukeInvalidDateTimeFormat(timeFormat);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
