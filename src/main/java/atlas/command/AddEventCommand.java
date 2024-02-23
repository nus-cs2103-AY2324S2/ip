package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.Event;

import java.time.LocalDateTime;

/**
 * This command adds a new event to the task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private int priority;

    /**
     * Constructs an {@code AddEventCommand} with the specified description and start and end times.
     *
     * @param tasks       The {@code TaskList} to which the deadline will be added.
     * @param ui          The {@code Ui} instance for user interaction.
     * @param storage     The {@code Storage} instance for saving the updated task list.
     * @param description The description of the Event.
     * @param start       The time by which the task starts.
     * @param end         The time which the task ends.
     */
    public AddEventCommand(TaskList tasks, Ui ui, Storage storage,
                           String description, LocalDateTime start, LocalDateTime end, int priority) {
        super(tasks, ui, storage);
        this.description = description;
        this.start = start;
        this.end = end;
        this.priority = priority;
    }

    /**
     * Executes the addition of a new event task.
     */
    @Override
    public String execute() {
        Event event = new Event(description, start, end, priority);
        tasks.addTask(event);
        return ui.showTaskAdded(tasks);
    }
}
