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
            return;
        }

        output.append("Get all these done bro:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
    }

    /**
     * Prints marked Task to user.
     *
     * @param task Task to be marked.
     */
    public void showMark(Task task) {
        output.append("Finally done bro.\n").append(task);
    }

    /**
     * Prints unmarked Task to user.
     *
     * @param task Task to be unmarked.
     */
    public void showUnmark(Task task) {
        output.append("Why are you not done yet bro?\n").append(task);
    }

    /**
     * Prints deleted Task to user.
     *
     * @param task Task to be deleted.
     */
    public void showDelete(Task task, TaskList tasks) {
        output.append("Task removed. Why so lazy bro?\n").append(task).append("\n")
                .append("You still have ").append(tasks.size()).append(" tasks in your list bro.");
    }

    /**
     * Prints added Task and number of Tasks in TaskList to user.
     *
     * @param task Task to be added.
     * @param tasks Tasks left in TaskList.
     */
    public void showAddTask(Task task, TaskList tasks) {
        output.append("Go get this done bro: \n").append(task).append("\n").append("You still have ")
                .append(tasks.size()).append(" tasks in your list bro.");
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
        }

        output.append("All these tasks match bro:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            output.append(i + 1).append(". ").append(foundTasks.get(i)).append("\n");
        }
    }

    /**
     * Returns the Ui response and resets the output String.
     *
     * @return String that is passed into GUI.
     */
    public String getOutput() {
        String temp = output.toString();
        assert !temp.equals("") : "Output should not be empty";
        output = new StringBuilder();
        return temp;
    }

}
