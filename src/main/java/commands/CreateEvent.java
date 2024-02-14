package commands;

import static utils.InputUtil.convertToDateTime;

import java.time.LocalDateTime;

import exception.InvalidEventException;
import objects.Events;
import objects.Task;
import objects.TaskList;
import view.CreatedTask;


/**
 * The CreateEvent class represents a command to create and add an event task to the TaskList.
 * It implements the Command interface and specifies the execution behavior for creating an event task.
 */
public class CreateEvent implements Command {

    /** The TaskList where the event task will be added. */
    private final TaskList tasks;

    /** The input array containing the name and event date information for the new task. */
    private final String[] input;

    /**
     * Constructs a CreateEvent command with the specified TaskList and input array.
     *
     * @param tasks The TaskList where the event task will be added.
     * @param input The input array containing the name and event date information for the new task.
     */
    public CreateEvent(TaskList tasks, String[] input) {
        this.tasks = tasks;
        this.input = input;
    }

    /**
     * Executes the CreateEvent command by creating a new event task, adding it to the TaskList,
     * and displaying a confirmation message.
     *
     * @return
     * @throws InvalidEventException If there is an issue with the event information provided.
     */
    @Override
    public String execute() throws InvalidEventException {
        String name = input[0];
        LocalDateTime from = convertToDateTime(input[1]);
        LocalDateTime to = convertToDateTime(input[2]);

        Task t = new Events(name, from, to);
        tasks.add(t);

        return CreatedTask.display(this.tasks, t);
    }
}
