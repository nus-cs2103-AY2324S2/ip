package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.task.Deadline;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Creates a DeadlineCommand object.
     *
     * @param args The arguments of the command.
     * @throws HenryException If the command is invalid.
     */
    public DeadlineCommand(String args) throws HenryException {
        if (!args.contains("/by")) {
            throw new HenryException("Missing /by");
        }
        String[] deadlineParams = args.split(" /by ");
        if (deadlineParams[0].isBlank()) {
            throw new HenryException("No description provided");
        }
        if (deadlineParams[1].isBlank()) {
            throw new HenryException("When this has to be done by?");
        }
        this.description = deadlineParams[0];
        this.by = deadlineParams[1];
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws HenryException {
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        storage.save(tasks);
        return String.format("Added this task\n%s\nNow you have %d tasks in the list :(\n",
                newDeadline,
                tasks.getNumOfTasks());
    }
}
