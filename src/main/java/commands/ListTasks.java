package commands;

import objects.TaskList;
import view.EncaseLines;

/**
 * The ListTasks class represents a command to display the list of tasks from the TaskList.
 * It implements the Command interface and specifies the execution behavior for listing tasks.
 */
public class ListTasks implements Command {

    /** The TaskList containing tasks to be listed. */
    private final TaskList tasks;

    /**
     * Constructs a ListTasks command with the specified TaskList.
     *
     * @param tasks The TaskList containing tasks to be listed.
     */
    public ListTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the ListTasks command by creating a formatted string representation of the tasks
     * and displaying it using the EncaseLines utility.
     *
     * @return
     */
    @Override
    public String execute() {
        StringBuilder output = new StringBuilder();

        if (tasks.isEmpty()) {
            return "List is Empty!";

        } else {

            for (int i = 0; i < tasks.size(); i++) {
                output.append(String.format("%d. %s", i + 1, tasks.get(i)));

                if (i < tasks.size() - 1) {
                    output.append("\n");
                }
            }

//            EncaseLines.display(output.toString());
        }

        return output.toString();
    }
}
