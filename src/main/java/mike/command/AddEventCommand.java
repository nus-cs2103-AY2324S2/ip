package mike.command;

import mike.MikeException;
import mike.Storage;
import mike.TaskList;
import mike.task.Event;
import mike.task.Task;

/**
 * Adds event to the task list.
 * @author ningc
 */
public class AddEventCommand extends AddCommand {
    private final String startDate;
    private final String endDate;

    /**
     * Constructor.
     * @param description What the event is about.
     * @param startDate When the event starts.
     * @param endDate When the event ends.
     */
    public AddEventCommand(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MikeException {
        Task newTask = new Event(description, startDate, endDate);
        taskList.add(newTask);
        return response(taskList, newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "ADD EVENT " + description + " " + startDate + " " + endDate;
    }
}
