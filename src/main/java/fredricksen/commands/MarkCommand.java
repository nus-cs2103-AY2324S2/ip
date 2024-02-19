package fredricksen.commands;

import fredricksen.tasks.Task;
import fredricksen.tasks.TaskList;

/**
 * Represents a "Mark" Command, which extends the Command class.
 * A "Mark" Command is a command that creates a MarkCommand object with
 * an array of each word of the user input command and
 * the TaskList tasks that stores the Task type object.
 */
public class MarkCommand extends Command {

    public MarkCommand(String[] fullCommandWords, TaskList tasks) {
        super(fullCommandWords, tasks);
    }

    /**
     * Goes through the TaskList tasks and sets all the Task's isDone to true.
     *
     * @param tasks The TaskList of tasks to iterate through.
     */
    public void markAll(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.getTask(i).setDone();
        }
    }

    /**
     * Executes the Mark command.
     * It sets the Task isDone field at task index that the user have input in the TaskList to true.
     *
     * @return A formatted String that is displayed after marking the tasks based on user input.
     */
    @Override
    public String execute() {
        try {
            Task currTask = this.getTasks().getTask(Integer.parseInt(this.getFullCommand()[1]) - 1);
            currTask.setDone();
            return "Nice! I've marked this task as done: \n"
                    + "    " + currTask;
        } catch (IndexOutOfBoundsException err) {
            String single = this.getTasks().size() <= 1 ? "task" : "tasks";
            int num = this.getTasks().size();
            return "You only have " + num + " "
                    + single
                    + " currently. Type \"list\" to view all your current "
                    + single;
        } catch (NumberFormatException err) {
            if (this.getFullCommand()[1].equalsIgnoreCase("all")) {
                markAll(this.getTasks());
            }
            return "Set all current tasks as completed";
        }
    }
}
