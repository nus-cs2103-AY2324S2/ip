package liv.processor;

import liv.task.Event;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents a command to add an event to the task list.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * The constructor of the class.
     * @param event The Event object to be added.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Add the event to the task list and signals the Ui to respond.
     * @param tasks The list of tasks to operate on.
     * @param ui The Ui to gives interaction with users.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(event);
        Ui.displayEventCommand(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChangedData() {
        return true;
    }
}
