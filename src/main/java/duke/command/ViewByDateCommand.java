package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing the action of viewing tasks by date.
 *
 * <p>The {@code ViewByDateCommand} class encapsulates the information and actions
 * required to view tasks based on a specified date. It inherits from the {@code Command} class
 * and implements the behavior specific to displaying tasks that occur on a particular date.</p>
 */
public class ViewByDateCommand extends Command {

    private LocalDate date;

    /**
     * Constructs a {@code ViewByDateCommand} object with the specified date.
     *
     * @param date The date for which tasks should be viewed.
     */
    public ViewByDateCommand(LocalDate date) {
        super("viewbydate", null);
        this.date = date;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) {
        List<Task> tasksByDate = tasks.getTasksByDate(date);

        // Displaying the date header
        ui.appendResponse(String.format("Here are your tasks on %s:\n", formatDate(date)));

        // Displaying tasks
        for (int i = 0; i < tasksByDate.size(); i++) {
            ui.appendResponse((i + 1) + ". " + tasksByDate.get(i));
        }

        return new TaskList(tasksByDate);
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }
}
