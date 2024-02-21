package command;

import cleo.DukeException;
import cleo.Task;
import cleo.TaskList;
import cleo.UI;

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
