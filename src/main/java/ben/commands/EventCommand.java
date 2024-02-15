package ben.commands;

import ben.storage.Storage;
import ben.tasks.Event;
import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add a new event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates an EventCommand with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     */
    public EventCommand(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Executes the EventCommand by adding a new Event task to the task list.
     *
     * @param tasks   The task list to which the new task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newEvent = new Event(false, description, startDate, endDate);
        tasks.addTask(newEvent);

        return ui.showAddedTaskMessage() +
                ui.show(newEvent.toString()) +
                ui.showCurrNoOfTasks(tasks);
    }
}
