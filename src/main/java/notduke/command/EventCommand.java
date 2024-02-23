package notduke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import notduke.notdukeexception.NotDukeException;
import notduke.notdukeexception.NotDukeInValidTimeframe;
import notduke.notdukeexception.NotDukeInvalidDateTimeFormat;
import notduke.notdukeexception.NotDukeMissingArgument;
import notduke.storage.Storage;
import notduke.task.Events;
import notduke.tasklist.TaskList;

public class EventCommand extends Command {
    private String inputs;

    public EventCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        try {
            String[] event = inputs.split(" /from ", 2);
            String[] time = event[1].split(" /to ");
            LocalDateTime from = LocalDateTime.parse(time[0], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse(time[1], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            boolean isValidTimeframe = to.isAfter(from);
            if (!isValidTimeframe) {
                throw new NotDukeInValidTimeframe();
            }
            return tasks.add(new Events(event[0], from, to, false));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NotDukeMissingArgument("event");
        } catch (DateTimeParseException e2) {
            throw new NotDukeInvalidDateTimeFormat("d/M/yyyy HHmm");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
