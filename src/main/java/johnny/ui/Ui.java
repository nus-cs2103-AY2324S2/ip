package johnny.ui;

import johnny.exceptions.JohnnyException;
import johnny.tasks.Task;
import johnny.tasks.TaskList;

/**
 * Handles all interactions with user.
 */
public class Ui {

    /** Output to be returned to GUI */
    private StringBuilder output = new StringBuilder();

    /**
     * Prints error message to user.
     *
     * @param errorMessage Error message of error to be printed.
     */
    public void showError(String errorMessage) {
        output.append(errorMessage);
    }

    /**
     * Prints welcome message to user.
     */
    public void showWelcome() {
        output.append("Johnny here. What do you want bro?");
    }

    /**
     * Prints end message to user.
     */
    public void showEnd() {
        output.append("Bye bro. I'm going back to sleep.");
    }

    /**
     * Prints all Tasks in TaskList to user.
     *
     * @param tasks TaskList to be printed
     * @throws JohnnyException Ignore as for loop will prevent error.
     */
    public void showList(TaskList tasks) throws JohnnyException {
        if (tasks.size() == 0) {
            output.append("You have no tasks bro. Stop being lazy and add some tasks.");
        } else {
            output.append("Get all these done bro:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append(i + 1 + ". " + tasks.get(i) + "\n");
            }
        }
    }

    /**
     * Prints marked Task to user.
     *
     * @param task Task to be marked.
     */
    public void showMark(Task task) {
        output.append("Finally done bro.\n");
        output.append(task);
    }

    /**
     * Prints unmarked Task to user.
     *
     * @param task Task to be unmarked.
     */
    public void showUnmark(Task task) {
        output.append("Why are you not done yet bro?\n");
        output.append(task);
    }

    /**
     * Prints deleted Task to user.
     *
     * @param task Task to be deleted.
     * @param tasks TaskList that the Task belonged to.
     */
    public void showDelete(Task task, TaskList tasks) {
        output.append("Task removed. Why so lazy bro?\n");
        output.append(task + "\n");
        output.append("You still have " + tasks.size() + " tasks in your list bro.");
    }

    /**
     * Prints added Task and number of Tasks in TaskList to user.
     *
     * @param task Task to be added.
     * @param tasks Tasks left in TaskList.
     */
    public void showAddTask(Task task, TaskList tasks) {
        output.append("Go get this done bro: \n");
        output.append(task + "\n");
        output.append("You still have " + tasks.size() + " tasks in your list bro.");
    }

    /**
     * Prints the Tasks found that matches user query.
     *
     * @param foundTasks List of Tasks that matches user query.
     * @throws JohnnyException Ignore as loop prevents error from being thrown.
     */
    public void showFoundTasks(TaskList foundTasks) throws JohnnyException {
        if (foundTasks.size() == 0) {
            output.append("No matches bro.");
        } else {
            output.append("All these tasks match bro:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                output.append(i + 1 + ". " + foundTasks.get(i) + "\n");
            }
        }
    }

    /**
     * Returns the Ui response and resets the output String.
     *
     * @return String that is passed into GUI.
     */
    public String getOutput() {
        String temp = output.toString();
        output = new StringBuilder();
        return temp;
    }

}
