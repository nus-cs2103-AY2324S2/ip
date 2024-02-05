package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.task.Event;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates a EventCommand object.
     * @param args The arguments of the command.
     * @throws HenryException If the command is invalid.
     */
    public EventCommand(String args) throws HenryException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new HenryException("Missing /from or /to");
        }
        String description = "";
        String from = "";
        String to = "";
        String[] parts = args.split(" /from | /to ");

        if (parts.length < 3) {
            throw new HenryException("Incorrect command format");
        }

        description = parts[0];
        if (args.indexOf("/from") < args.indexOf("/to")) {
            from = parts[1];
            to = parts[2];
        } else {
            from = parts[2];
            to = parts[1];
        }

        if (description.isBlank()) {
            throw new HenryException("Please provide description");
        }
        if (from.isBlank()) {
            throw new HenryException("Missing /from");
        }
        if (to.isBlank()) {
            throw new HenryException("Missing /to");
        }

        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws HenryException {
        Event newEvent = new Event(this.description, this.from, this.to);
        tasks.addTask(newEvent);
        storage.save(tasks);
        return String.format("Added this task\n%s\nNow you have %d tasks in the list :(\n",
                newEvent,
                tasks.getNumOfTasks());
    }
}
