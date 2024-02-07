package command;

import java.time.LocalDate;
import java.util.ArrayList;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Task;

public class SearchDateCommand extends Command {
    private final String date;

    public SearchDateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        LocalDate localDate = Task.parseDate(date);
        taskList.searchDate(localDate);
    }
}
