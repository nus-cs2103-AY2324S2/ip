package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
/**
 * Command to create a new event.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs a new EventCommand.
     *
     * @param description Description of event to be created.
     * @param start Start date of event.
     * @param end End date of event.
     */
    public EventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command, creating a new event, adding it to the list, saving to storage.
     * Also updates response
     *
     * @param list TaskList object containing current tasks.
     * @param storage To update storage with new event task.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        Task newEvent = Task.createTask(TaskType.EVENT, description, false, start, end);
        list.add(newEvent);
        storage.save(list);

        super.setResponse("added new event: " + newEvent
                + "\nLooks like you have " + list.countTasks() + " things left to do!");
    }
}
