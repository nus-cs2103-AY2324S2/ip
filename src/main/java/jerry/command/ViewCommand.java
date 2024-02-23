package jerry.command;
import jerry.Task;
import jerry.TaskList;
import jerry.ToDo;
import jerry.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A command to view tasks scheduled for a specific date.
 * <p>
 * This command filters the task list for tasks that occur on the specified date and displays them to the user.
 * It requires a date string in the format YYYY-MM-DD to operate.
 */
public class ViewCommand extends Command {
    private final String dateString;

    /**
     * Creates a ViewCommand with the specified UI, task list, and date string.
     *
     * @param ui         The UI component for interacting with the user.
     * @param tasks      The task list from which to find tasks.
     * @param dateString The target date for which tasks should be displayed, in YYYY-MM-DD format.
     */
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

