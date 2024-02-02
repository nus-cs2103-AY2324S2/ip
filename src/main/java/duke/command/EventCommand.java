package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.task.Events;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    private String inputs;

    public EventCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String timeFormat = "d/M/yyyy HHmm";
        try {
            String[] event = inputs.split(" /from ", 2);
            String[] time = event[1].split(" /to ");
            LocalDateTime from = LocalDateTime.parse(time[0], DateTimeFormatter.ofPattern(timeFormat));
            LocalDateTime to = LocalDateTime.parse(time[1], DateTimeFormatter.ofPattern(timeFormat));
            ui.sendReply(tasks.add(new Events(event[0], from, to, false)));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgument(3, "event");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
