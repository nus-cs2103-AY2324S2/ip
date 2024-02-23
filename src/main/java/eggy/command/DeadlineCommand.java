package eggy.command;

import java.time.LocalDateTime;

import eggy.exception.DateTimeFormatException;
import eggy.exception.DuplicatedTaskException;
import eggy.exception.IncompleteTaskException;
import eggy.exception.SaveTasksException;
import eggy.response.Response;
import eggy.storage.Storage;
import eggy.task.Deadline;
import eggy.task.TaskList;

/**
 * Represents a command to add a deadline to the task list.
 */
public class DeadlineCommand extends Command {
    /** The name of the deadline task. */
    private String name;
    /** The deadline of the deadline task. */
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param commands The array of commands.
     * @throws IncompleteTaskException If the task description is incomplete.
     * @throws DateTimeFormatException If the date and time format is invalid.
     */
    public DeadlineCommand(String... commands) throws IncompleteTaskException, DateTimeFormatException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("deadline");
        }
        String[] deadlineSplit = commands[1].split(" /by ");
        if (deadlineSplit.length < 2) {
            throw new IncompleteTaskException("deadline");
        }
        this.name = deadlineSplit[0];
        this.by = Command.parseDateTime(deadlineSplit[1]);
    }

    /**
     * Adds a deadline to the task list and saves the task list to the storage.
     *
     * @param tasks The task list of the chatbot.
     * @param response The response of the chatbot.
     * @param storage The storage of the chatbot.
     * @throws SaveTasksException If there is an error saving the task list to the storage.
     * @throws DuplicatedTaskException If the task to be added is already in the task list.
     */
    @Override
    public void execute(TaskList tasks, Response response, Storage storage)
            throws SaveTasksException, DuplicatedTaskException {
        Deadline newDeadline = new Deadline(this.name, this.by);
        tasks.addTask(newDeadline);
        response.setTaskAddedResponse(newDeadline, tasks.getSize());
        storage.save(tasks);
    }

}
