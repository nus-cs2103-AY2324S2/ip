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

public class DateCommand extends Command {
    private LocalDate date;

    public DateCommand(LocalDate date) throws ZackException {
        this.date = date;
    }

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
