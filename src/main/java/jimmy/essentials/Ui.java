package jimmy.essentials;

import java.util.ArrayList;

import jimmy.tasks.Task;

/**
 * Represents the user interface of the bot.
 */
public class Ui {

    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public String greetUser() {
        return "Hello! I'm Jimmy\nWhat can I do for you?\n";
    }

    /**
     * Exits the bot.
     *
     * @return The goodbye message.
     */
    public String exit() {
        return "Goodbye for now. Jimmy hopes to see you again soon!\n";
    }

    /**
     * Display the number of tasks after the addition of a new task.
     *
     * @param newTask    The new task that was added.
     * @param tasksCount The number of tasks in the list.
     * @return The new task and the number of tasks in the list, in String format.
     */
    public String showNewTask(String newTask, int tasksCount) {
        return "Got it. I've added this task:" + "\n" + newTask + "\n"
                + generateListCounter(tasksCount);
    }

    /**
     * Generates the counter for the number of tasks in the task list.
     *
     * @return The counter for the task list.
     */
    private String generateListCounter(int counter) {
        assert counter >= 0;
        if (counter == 0) {
            return "You have no jimmy.tasks, create some now!";
        } else if (counter == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + counter + " tasks in the list.";
        }
    }

    /**
     * Display the number of tasks left after the deletion of a task.
     *
     * @param deletedTask The task that was deleted.
     * @param tasksCount  The number of tasks in the list.
     * @return The deleted task and the number of tasks in the list, in String format.
     */
    public String showDeletedTask(String deletedTask, int tasksCount) {
        return "Noted. I've removed this task:" + "\n" + deletedTask + "\n"
                + "Now you have " + tasksCount + " tasks in the list.\n";
    }

    /**
     * Display all stored tasks.
     *
     * @param taskList The list of tasks.
     * @return The list of tasks, in String format.
     */
    public String showAllTasks(ArrayList<Task> taskList) {
        assert taskList != null;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the jimmy.tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(".").append(taskList.get(i).toString());
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    /**
     * Display the marked task.
     *
     * @param task The task that was marked.
     * @return The marked task, in String format.
     */
    public String showMarkedTask(String task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Display the unmarked task.
     *
     * @param task The task that was unmarked.
     * @return The unmarked task, in String format.
     */
    public String showUnmarkedTask(String task) {
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    /**
     * Display the found tasks.
     *
     * @param foundTasks The tasks that were found.
     * @return The found tasks, in String format.
     */
    public String showFoundTasks(String foundTasks) {
        return "Here are the matching jimmy.tasks in your list:" + "\n" + foundTasks;
    }
}
