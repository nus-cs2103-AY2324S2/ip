package arc.commands;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.Event;
import arc.tasks.TaskList;

/**
 * Represents the command for adding an event task to the task list in the Arc application.
 */
public class CommandEvent extends Command {
    private final Event event;

    /**
     * Constructs a new CommandEvent object with the specified event task.
     *
     * @param event The event task to be added to the task list.
     */
    public CommandEvent(Event event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        tasks.add(this.event);

        storage.saveTasks(tasks);

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.event, tasks.size());
    }
}
