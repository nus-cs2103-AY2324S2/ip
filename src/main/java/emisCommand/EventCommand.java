package emisCommand;

import emis.TaskList;
import emis.Storage;
import emisTask.Event;

/**
 * The EventCommand class represents a command to add an event task in the EMIS application.
 * When executed, it adds a new event task with the specified description, start time, and end time to the task list.
 */
public class EventCommand extends Command {
    /** The description of the event task. */
    private String description;

    /** The start time of the event task. */
    private String from;

    /** The end time of the event task. */
    private String to;

    /**
     * Constructs a new EventCommand object with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by adding a new event task to the task list and updating the storage.
     *
     * @param tasklist The TaskList object representing the list of tasks.
     * @param storage The Storage object handling loading and saving of tasks.
     * @return A message indicating the result of executing the command.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        String response = tasklist.addTask(new Event(this.description, this.from, this.to));
        storage.updateStorage();
        return response;
    }
}
