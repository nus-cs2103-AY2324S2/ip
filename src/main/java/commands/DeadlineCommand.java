package commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.DeadlineFormatException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;

/**
 * Represents the command used to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private static final String SUCCESS_MESSAGE = "Got it. Uncle added this deadline:\n\t %s "
            + "\n Now you have %s task(s) in the list.";
    private final String message;

    /**
     * Creates a new DeadlineCommand object with the provided message.
     *
     * @param message Input message containing description and deadline.
     */
    public DeadlineCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the DeadlineCommand, adding a deadline task to the task list based on the provided input message.
     * The input message is expected to contain a description and a deadline, separated by "/by".
     * The description represents the task's details, and the deadline is the date by which the task must be completed.
     * If the input does not follow the correct format or if the date is invalid, a DeadlineFormatException is thrown.
     *
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @throws DeadlineFormatException Thrown when the input does not follow the correct deadline command format
     *                                or if the provided deadline date is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DeadlineFormatException {
        String[] args = message.split("/by");
        if (args.length == 1 || args.length > 2) {
            throw new DeadlineFormatException();
        }
        String desc = args[0].trim();
        String by = args[1].trim();

        try {
            Task deadline = new Deadline(desc, LocalDate.parse(by));
            tasks.addTasks(deadline);
            storage.appendToFile(tasks);
            return String.format(SUCCESS_MESSAGE, deadline, tasks.numTasks());
        } catch (IOException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            throw new DeadlineFormatException();
        }
    }
}
