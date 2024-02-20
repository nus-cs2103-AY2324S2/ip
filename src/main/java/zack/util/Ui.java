package zack.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import zack.ZackException;
import zack.tasks.Deadline;
import zack.tasks.Task;

/**
 * Handles user interaction and displays messages to the user.
 */
public class Ui {
    // There is no constructor as the Ui class does not need to perform any initialization anymore.

    /**
     * Shows a message when a task is added, along with the current number of tasks.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks after adding.
     * @return
     */
    public String showAddedTask(Task task, int totalTasks) {
        return "Righty-ho! I've added this task:\n  " + task + "\nNow you have " + totalTasks
                + " tasks in the list. All the best!";
    }

    /**
     * Shows a message when a task is deleted, along with the current number of tasks.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after deletion.
     * @return
     */
    public String showDeletedTask(Task task, int totalTasks) {
        return "Gotcha, let's get rid of that task for ya. I've removed:\n" + task + "\nNow you "
                + "have " + totalTasks + " tasks left the list.";
    }

    /**
     * Prints the goodbye message when the user exits the application.
     */
    public String showGoodbyeMessage() {
        return "Bye bye! Hope to see you again soon :)!";
    }

    /**
     * Shows a message when a task is marked as done or undone.
     *
     * @param task   The task that was marked.
     * @param isDone True if the task is marked as done, false otherwise.
     */
    public String showMarkedTask(Task task, boolean isDone) {
        if (isDone) {
            return "Good job completing your task! It's now officially done:\n" + task;
        } else {
            return "Alright run it back boys, this task ain't over just yet:\n" + task;
        }
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public String showTaskList(ArrayList<Task> tasks, String ascOrDesc, String sortType) {
        if (tasks.isEmpty()) {
            return "Nice! There are no tasks to do now.";
        }
        StringBuilder sb = new StringBuilder();
        if (sortType.equalsIgnoreCase("alpha")) {
            sb.append("Here are your tasks sorted in ").append(ascOrDesc).append(" alphabetical order:\n");
        } else if (sortType.equalsIgnoreCase("addtime")) {
            sb.append("Here are your tasks sorted in ").append(ascOrDesc).append(" added order:\n");
        } else {
            // normally display all tasks
            sb.append("Here are all tasks in your list:\n");
        }
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates a formatted string representation of a list of deadlines, sorted based on their added time.
     *
     * @param tasks     the list of deadlines to display.
     * @param ascOrDesc a string indicating the order in which the deadlines are sorted ("asc" for ascending,
     *                 "desc" for descending).
     * @return a formatted string representing the sorted list of deadlines, along with an indication of the
     *     sorting order.
     */
    public String showDeadlinesList(List<Deadline> tasks, String ascOrDesc) {
        if (tasks.isEmpty()) {
            return "No deadlines coming up, time to kick back and relax 😋";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your deadlines sorted in " + ascOrDesc + " added order:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays tasks on a specific date to the user.
     *
     * @param date  The date for which tasks are displayed.
     * @param tasks The list of tasks happening on the specified date.
     */
    public String showTasksOnDate(LocalDate date, ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You haven't got tasks related to this date. Maybe try another date?";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks on ").append(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).append(":\n");
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays an error message when loading tasks fails.
     *
     * @param e The exception that occurred while loading tasks.
     */
    public void showLoadingError(ZackException e) {
        System.err.println("Houston, we've got a problem: " + e.getMessage()); // Display the exception
        // message
    }

    /**
     * Displays a list of tasks that have been found based on a search operation.
     *
     * This method is used to display a list of tasks that match a search criteria
     * in the specified TaskList. If no matching tasks are found, it prints a message
     * indicating that no tasks were found. If matching tasks are found, it prints a
     * numbered list of those tasks with their details.
     *
     * @param foundTasks The TaskList containing the matching tasks to display.
     * @throws ZackException If there is an error or exception while displaying
     *                      the found tasks.
     */
    public String showFoundTasks(TaskList foundTasks) throws ZackException {
        StringBuilder sb = new StringBuilder();
        if (foundTasks.getSize() == 0) {
            sb.append("There's no such task in your list brah!");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.getSize(); i++) {
                sb.append((i + 1)).append(".").append(foundTasks.getTask(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
