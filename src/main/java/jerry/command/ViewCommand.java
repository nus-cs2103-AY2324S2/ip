package jerry.command;
import jerry.Task;
import jerry.TaskList;
import jerry.ToDo;
import jerry.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ViewCommand extends Command {
    private final String dateString;

    public ViewCommand(Ui ui, TaskList tasks, String dateString) {
        super(ui, tasks);
        this.dateString = dateString;
    }

    @Override
    public String execute() {
        try {
            if (dateString.trim().isEmpty()) {
                throw new CommandFormatException("Error: PLease include date \nUsage: view YYYY-MM-DD");
            }
            LocalDate date = LocalDate.parse(dateString);
            ArrayList<Task> tasksForDate = tasks.getTasksForDate(date);
            return ui.showTasksForDate(tasksForDate, date);
        } catch (CommandFormatException e) {
            return ui.showMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.showMessage("Error: Wrong date format. Please use the format YYYY-MM-DD.");
        }
    }
}

