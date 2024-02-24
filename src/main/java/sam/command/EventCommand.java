package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.task.Event;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    /**
     * Constructs an EventCommand with the specified event details.
     *
     * Initializes an EventCommand with the provided event details. If the task info does not contain
     * "/from" or "/to" indicating the event's start and end times, throws a SamException with a message
     * indicating the invalid format. If any of the event details (description, start time, end time) are blank,
     * throws a SamException with a message indicating the need to provide all required details.
     *
     * @param taskInfo the string containing event details (description, start time, end time)
     * @throws SamException if the task info is missing "/from" or "/to", or if any of the event details are blank
     */
    public EventCommand(String taskInfo) throws SamException {
        if (!taskInfo.contains("/from") || !taskInfo.contains("/to")) {
            throw new SamException("Invalid format for event, please provide event details by using /from and /to.");
        }
        String[] details = taskInfo.split(" /from | /to ");
        if (details.length < 3) {
            throw new SamException("Please check whether you have provided a description and both start/end time.");
        }

        String description = details[0];
        String from = "";
        String to = "";
        if (taskInfo.indexOf("/from") < taskInfo.indexOf("/to")) {
            from = details[1];
            to = details[2];
        } else {
            from = details[2];
            to = details[1];
        }

        if (description.isBlank()) {
            throw new SamException("Please provide a description");
        }
        if (from.isBlank()) {
            throw new SamException("Missing /from");
        }
        if (to.isBlank()) {
            throw new SamException("Missing /to");
        }
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws SamException {
        Event newTask = new Event(this.description, this.from, this.to);
        tasks.addTask(newTask);
        storage.save(tasks);
        return String.format("The task has been added to the list:\n%s\nNow you have %d tasks left.\n",
                newTask, tasks.getNumOfTasks());
    }
}
