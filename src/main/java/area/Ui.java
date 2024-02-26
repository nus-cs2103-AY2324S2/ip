package area;

import java.util.ArrayList;

/**
 * Returns responses to user instructions in the form of printed statements. It
 * starts with greeting the user and ends with the command bye. If any task is
 * added or modified,
 * Ui will return a statement accordingly to inform user that user's instructions
 * have been followed.
 */
public class Ui {

    /**
     * Creates class object UI.
     */
    public Ui() {
    };

    /**
     * Returns a message welcoming the user to utilise the program.
     * 
     * @return Greets the user with an initial message.
     */
    public String greetUser() {
        return "Hello! I'm Area.\n"
                + "What can I do for you?\n";
    }

    /**
     * Returns a statement to acknowledge task has been added.
     * 
     * @param tasks List of tasks.
     * @return Acknowledges addition of new task.
     */
    public String addTask(TaskList tasks) {
        return "Got it. I've added this task:\n" + tasks.getTaskList().get(tasks.getNumberOfTasks() - 1).toString()
                + "\n" + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list" + ".\n";
    }

    /**
     * Returns a statement acknowledging a task has been marked done.
     * 
     * @param num   index of task in list.
     * @param tasks List of tasks.
     * @return Statement stating completion of task.
     */
    public String taskDone(int num, TaskList tasks) {
        return "Nice! I've marked this task as done:\n "
                + tasks.getTaskList().get(num - 1).toString() + "\n";
    }

    /**
     * Returns a statement to show task is undone again
     * 
     * @param num   index of task in list.
     * @param tasks List of tasks.
     * @return Statement confirming task is now undone
     */
    public String taskUndone(int num, TaskList tasks) {
        return "OK, I've marked this task as not done yet:\n "
                + tasks.getTaskList().get(num - 1).toString() + "\n";
    }

    /**
     * Returns a statement to show task has been deleted
     * 
     * @param deletedTask Task to be deleted.
     * @param tasks       List of tasks.
     * @return Statement showing that deletion of task.
     */
    public String deleteTask(Task deletedTask, TaskList tasks) {
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\n" + "Now you have "
                + tasks.getNumberOfTasks() + " tasks in the list.\n";
    }

    /**
     * Returns the list of tasks
     * 
     * @param tasks List of tasks.
     * @return Prints all tasks in the given list sequentially.
     */
    public String showList(ArrayList<Task> tasks) {
        String result = "Here are the tasks:\n";
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                result += i + 1 + "." + tasks.get(i).toString() + "\n";
            }
        } else {
            result = "There are no tasks. Please add some tasks first!\n";
        }
        return result;
    }

    /**
     * Ends chat with user and closes program.
     * 
     * @return Message telling goodbye.
     */
    public String endChat() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Confirms change in priority level for task.
     * 
     * @param task Task whose priority changed.
     * @return Confirmation message indicating change in priority.
     */
    public String priorityMessage(Task task) {
        return "Priority hase been set to " + task.getPriority() + " for " + task.toString();
    }

}
