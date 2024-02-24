package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.task.Deadline;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;
    /**
     * Constructs a DeadlineCommand with the specified task information.
     *
     * Initializes a DeadlineCommand with the provided task information. If the task information is blank,
     * throws a SamException with a message indicating the need to provide task details.
     *
     * @param taskInfo the task information for creating the deadline task
     * @throws SamException if the provided task information is blank
     */
    public DeadlineCommand(String taskInfo) throws SamException {
        if (!taskInfo.contains("/by")) {
            throw new SamException("Invalid format for deadline, please provide a deadline using /by.");
        }

        String[] details = taskInfo.split(" /by ");
        if (details.length < 2) {
            throw new SamException("Invalid format for deadline, please provide a deadline using /by.");
        }
        if (details[0].isBlank()) {
            throw new SamException("Please provide a description of the task");
        }
        if (details[1].isBlank()) {
            throw new SamException("Please provide a deadline?");
        }
        this.description = details[0];
        this.by = details[1];
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws SamException {
        Deadline newTask = new Deadline(description, by);
        tasks.addTask(newTask);
        storage.save(tasks);
        return String.format("I've added this task \n%s\n You have %d tasks left in the list.\n",
                newTask, tasks.getNumOfTasks());
    }
}
