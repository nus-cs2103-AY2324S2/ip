package commands;

import Exceptions.InvalidInputException;
import TaskList.Tasks.Task;
import TaskList.Tasks.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline to the task list.
 * A <code>AddDeadlineCommand</code> object corresponds to a command with a description and a deadline
 * e.g., <code>"deadline return book /by 2020-12-12 1800"</code>
 */
public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    private final Task deadline;

    /**
     * Constructs an AddDeadlineCommand object with a description and a deadline.
     * @param deadlineName the description of the deadline
     * @param by the deadline of the task
     * @throws InvalidInputException if the input is invalid
     */
    public AddDeadlineCommand(String deadlineName, LocalDateTime by) throws InvalidInputException {
        this.deadline = new Deadline(deadlineName, by);
    }

    /**
     * Executes the command to add a deadline to the task list.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute() throws IOException {
        assert this.deadline != null : "Deadline object should not be null.";
        this.taskList.addTask(this.deadline);
        return "I have added this task:\n" + this.deadline + "\n" +
                "Now you have " + this.taskList.size() + " tasks in your list.";

    }
}
