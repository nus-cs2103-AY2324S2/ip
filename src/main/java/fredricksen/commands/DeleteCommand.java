package fredricksen.commands;

import fredricksen.tasks.Task;
import fredricksen.tasks.TaskList;

/**
 * Represents a "Delete" Command, which extends the Command class.
 * A "Delete" Command is a command that creates a DeleteCommand object with
 * an Array of each word from the user input command and the TaskList tasks to store the Task type object.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand instance with the specified user input command
     * and the TaskList to store the Task type task in.
     *
     * @param fullCommandWords The user input command to be executed.
     * @param tasks the TaskList to store the various tasks.
     */
    public DeleteCommand(String[] fullCommandWords, TaskList tasks) {
        super(fullCommandWords, tasks);
    }

    /**
     * A function to format the number of Task remaining in the TaskList.
     *
     * @param numTask Number of task in the TaskList
     * @param single singular or plural form of String based on how many tasks there are.
     * @return A formatted String to tell the user how many tasks there are in the TaskList.
     */
    public String formatTaskString(int numTask, String single) {
        return "You only have "
                + numTask + " " + single
                + " currently. Type \"list\" to view all your current "
                + single;
    }

    /**
     * A function to format the String after removing a Task from TaskList.
     *
     * @param currTask the Task that have been removed by the user input command.
     * @param numTask Number of task in the TaskList
     * @param single singular or plural form of String based on how many tasks there are.
     * @return A formatted String to tell the user about the Task that have been removed.
     */
    public String formatRemoveTaskString(Task currTask, int numTask, String single) {
        return "Noted. I've removed this task:\n"
                + "    " + currTask + "\n"
                + "Now you have " + numTask + " " + single + " in the task list.";
    }

    /**
     * Executes the Delete command.
     * It tries to get the latest task that was added to the TaskList and deletes that from the TaskList.
     * It then returns a formatted String about the Task that have just been deleted.
     * If it fails then it returns the formatted String to tell the user about the remaining about of Tasks.
     * Else if either deletes all the task or ask the user to key in a valid input instead.
     *
     * @return A String that is the message to be displayed after trying to remove a task from TaskList.
     */
    @Override
    public String execute() {
        String single = this.getTasks().size() <= 1 ? "task" : "tasks";
        try {
            Task currTask = this.getTasks().getTask(Integer.parseInt(this.getFullCommand()[1]) - 1);
            this.getTasks().deleteTask(Integer.parseInt(this.getFullCommand()[1]) - 1);
            return formatRemoveTaskString(currTask, this.getTasks().size(), single);
        } catch (IndexOutOfBoundsException err) {
            return formatTaskString(this.getTasks().size(), single);
        } catch (NumberFormatException err) {
            if (this.getFullCommand()[1].equalsIgnoreCase("all")) {
                this.getTasks().clearList();
            } else {
                return "Please enter a valid input";
            }
            return "Clearing all current tasks in the list";
        }
    }
}
