package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.task.ToDo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;
    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * Initializes a TodoCommand with the provided task description. If the task description is blank,
     * throws a SamException with a message indicating the need to provide a task description.
     *
     * @param taskInfo the description of the Todo task
     * @throws SamException if the provided task description is blank
     */
    public TodoCommand(String taskInfo) throws SamException {
        if (taskInfo.isBlank()) {
            throw new SamException("Please provide a task description.");
        }
        this.description = taskInfo;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws SamException {
        ToDo newTask = new ToDo(description);
        tasks.addTask(newTask);
        storage.save(tasks);
        return String.format("This task has been added to the list:\n%s\nNow you have %d tasks left.\n",
                newTask, tasks.getNumOfTasks());
    }
}
