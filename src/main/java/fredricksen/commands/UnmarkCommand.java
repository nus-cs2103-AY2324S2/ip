package fredricksen.commands;

import fredricksen.tasks.Task;
import fredricksen.tasks.TaskList;

/**
 * Represents an "Unmark" Command, which extends the Command class.
 * An "Unmark" Command is a command that creates a UnmarkCommand object with
 * an array of each word of the user input command and
 * the TaskList tasks that stores the Task type object.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(String[] fullCommandWords, TaskList tasks) {
        super(fullCommandWords, tasks);
    }

    /**
     * Goes through the TaskList tasks and sets all the Task's isDone to false.
     *
     * @param tasks The TaskList of tasks to iterate through.
     */
    public void unmarkAll(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.getTask(i).setUndone();
        }
    }

    /**
     * Executes the Mark command.
     * It sets the Task isDone field at task index that the user have input in the TaskList to false.
     *
     * @return A formatted String that is displayed after marking the tasks based on user input.
     */
    @Override
    public String execute() {
        try {
            Task currTask = this.getTasks().getTask(Integer.parseInt(this.getFullCommand()[1]) - 1);
            currTask.setUndone();
            return "Okay, I've marked this task as not done yet: \n"
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
                unmarkAll(this.getTasks());
            } else {
                return "Please enter a valid input";
            }
            return "Set all current tasks as uncompleted";
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnmarkCommand;
    }
}
