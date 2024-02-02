package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeInvalidDateTimeFormat;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class CheckCommand extends Command {
    private String inputs;

    public CheckCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(inputs, DateTimeFormatter.ofPattern("d/M/yyyy"));
            ui.sendReply(tasks.check(date));
        } catch (DateTimeParseException e1) {
            throw new DukeInvalidDateTimeFormat("d/M/yyyy");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
