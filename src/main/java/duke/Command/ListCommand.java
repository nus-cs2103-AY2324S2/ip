package duke.Command;
import duke.TaskList;
import duke.DukeException;
import duke.Command.Command;
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, String command) {
        if (taskList.getTasks().isEmpty()) {
            return "You have no tasks in your list.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                sb.append((i + 1) + "." + taskList.getTask(i) + "\n");
            }
            return sb.toString();
        }
    }
}