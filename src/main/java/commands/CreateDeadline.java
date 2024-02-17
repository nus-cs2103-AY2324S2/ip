package commands;

import static utils.InputUtil.convertToDateTime;

import java.time.LocalDateTime;

import exception.InvalidDeadlineException;
import objects.Deadlines;
import objects.Task;
import objects.TaskList;
import view.CreatedTask;

/**
 * The CreateDeadline class represents a command to create and add a deadline task to the TaskList.
 * It implements the Command interface and specifies the execution behavior for creating a deadline task.
 */
public class CreateDeadline implements Command {
    /** The TaskList where the deadline task will be added. */
    private final TaskList tasks;
    /** The input array containing the name and deadline information for the new task. */
    private final String[] input;

    /**
     * Constructs a CreateDeadline command with the specified TaskList and input array.
     *
     * @param tasks The TaskList where the deadline task will be added.
     * @param input The input array containing the name and deadline information for the new task.
     */
    public CreateDeadline(TaskList tasks, String[] input) {
        this.tasks = tasks;
        this.input = input;
    }

    /**
     * Executes the CreateDeadline command by creating a new deadline task, adding it to the TaskList,
     * and displaying a confirmation message.
     *
     * @return String
     * @throws InvalidDeadlineException If there is an issue with the deadline information provided.
     */
    @Override
    public String execute() throws InvalidDeadlineException {
        String name = input[0];
        LocalDateTime by = convertToDateTime(input[1]);

        Task t = new Deadlines(name, by);
        tasks.add(t);

        return CreatedTask.display(this.tasks, t);
    }
}
