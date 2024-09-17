package fluffy.command;

import java.time.LocalDate;

import fluffy.storage.Storage;
import fluffy.task.Event;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    protected String description;
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for EventCommand.
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        ui.showTaskAdded(event, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
