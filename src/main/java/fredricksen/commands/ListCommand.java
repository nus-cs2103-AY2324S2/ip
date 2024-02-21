package fredricksen.commands;

import fredricksen.tasks.TaskList;

/**
 * Represents a "List" Command, which extends the Command class.
 * A "List" Command is a command that creates a ListCommand object with
 * the TaskList tasks that stores the Task type object.
 */
public class ListCommand extends Command {

    private TaskList tasks;

    /**
     * Constructs an FindCommand instance with the TaskList that store the Task type tasks in.
     * @param tasks The TaskList of Task type tasks.
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the List command.
     * It goes through the TaskList and outputs all the tasks in the TaskList
     * in a formatted String.
     *
     * @return A formatted String that displays all the tasks in the TaskList.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= this.tasks.size(); i++) {
            sb.append(i).append(". ");
            sb.append(this.tasks.getTask(i - 1)).append("\n");
        }
        return sb.toString();
    }
}
