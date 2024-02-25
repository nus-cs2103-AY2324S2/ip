package thames.command;

import thames.Storage;
import thames.TaskList;
import thames.Ui;
import thames.task.Task;
import thames.task.Event;

import java.time.LocalDate;

/**
 * Subclass of Command that deals with displaying the events for the specified date..
 */
public class ScheduleCommand extends Command{
    LocalDate scheduleDate;
    TaskList events;

    public ScheduleCommand(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
        events = new TaskList();
    }

    /**
     * Outputs a list of events for the specified date.
     *
     * @return String message to be shown by chat bot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.getDone() || !(task instanceof Event)) {
                continue;
            }

            LocalDate fromDate = ((Event) task).getFrom();
            LocalDate toDate = ((Event) task).getTo();

            if (scheduleDate.isAfter(fromDate) && scheduleDate.isBefore(toDate)) {
                events.add(task);
            }
        }

        return ui.showList(events);
    }

}
