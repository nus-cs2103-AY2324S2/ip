package duke.command;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a task with a deadline.
 */
public class DeadlineCommand extends Command {
    private String taskDescription;
    private String dueTime;

    /**
     * Constructs a new deadline command.
     *
     * @param body The body of the command.
     */
    public DeadlineCommand(String body) {
        super(body);
        String[] parts = body.split("/by", 2);
        this.taskDescription = parts[0].trim();
        this.dueTime = parts.length > 1 ? parts[1].trim() : "";
    }

    /**
     * Executes the command. This command adds a task with a deadline to the task
     * list. The program state is set to normal after the command is executed, even
     * if the command fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the user input is invalid, or if the command fails
     *                       to execute.
     */
    @Override
    public String execute(TaskList list, ProgramState state) throws DukeException {
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty.",
                    "The description of a deadline cannot be empty.");
        } else if (dueTime.isEmpty()) {
            throw new EmptyDateTimeException("The due time of a deadline cannot be empty.",
                    "The due time of a deadline cannot be empty.");
        }
        Task task = new Deadline(taskDescription, dueTime);
        list.addTask(task);
        String response = ("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        return response;
    }
}
