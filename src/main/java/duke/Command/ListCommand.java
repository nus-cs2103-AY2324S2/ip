package duke.Command;

import duke.TaskList;



/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command and returns a string representation of the tasks in the task list.
     *
     * @param taskList the task list containing the tasks
     * @param command  the command string
     * @return a string representation of the tasks in the task list
     */
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