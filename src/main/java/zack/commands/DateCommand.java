package zack.commands;

import zack.ZackException;
import zack.tasks.Deadline;
import zack.tasks.Event;
import zack.tasks.Task;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Command class responsible for displaying tasks specific to a given date.
 */
public class DateCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a DateCommand with the specified date.
     *
     * @param date The date for which tasks will be displayed.
     * @throws ZackException If there is an error with the provided date.
     */
    public DateCommand(LocalDate date) throws ZackException {
        this.date = date;
    }

    /**
     * Executes the DateCommand, displaying tasks specific to the given date.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui to display user messages.
     * @param storage The Storage for task list data.
     * @throws ZackException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException {
        ArrayList<Task> dateSpecificTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                dateSpecificTasks.add(task);
            } else if (task instanceof Event && ((Event) task).isHappeningOnDate(date.atStartOfDay())) {
                dateSpecificTasks.add(task);
            }
        }
        ui.showTasksOnDate(date, dateSpecificTasks);
    }
}
