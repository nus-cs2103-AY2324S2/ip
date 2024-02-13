package duke.Command;
import duke.TaskList;
import duke.DukeException;
import duke.Task.Task;
import java.util.ArrayList;
public class DeleteCommand extends Command {
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        int index = Integer.parseInt(parts[1].trim()) - 1;
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }
        Task removedTask = taskList.removeTask(index);
        return "Noted. I've removed this task:\n" + removedTask + "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
    }
}
