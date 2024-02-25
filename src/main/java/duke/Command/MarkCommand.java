package duke.Command;

import duke.TaskList;
import duke.DukeException;
import duke.Task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    /**
     * Executes the mark command to mark a task as done.
     *
     * @param taskList the task list containing the tasks
     * @param command  the command string
     * @return the message indicating the task has been marked as done
     * @throws DukeException if the task number is invalid
     */
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        int index = Integer.parseInt(parts[1].trim()) - 1;
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = taskList.getTask(index);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task;
    }

}