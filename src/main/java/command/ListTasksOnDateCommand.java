package command;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListTasksOnDateCommand extends Command {
    private String dateString;

    public ListTasksOnDateCommand(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public String execute(TaskList tasks, UI ui) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.getTasksOnDate(dateString);
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy")); // Re-parse for display
        return ui.showTasksOnDate(filteredTasks, date);
    }
}
