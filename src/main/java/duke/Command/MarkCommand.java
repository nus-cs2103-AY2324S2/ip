package duke.Command;
import duke.TaskList;
import duke.DukeException;
import duke.Task.Task;

public class MarkCommand extends Command{
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