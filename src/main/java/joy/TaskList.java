package joy;

import java.util.ArrayList;

import joy.task.Task;



/**
 * TaskList class represents a list of tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {

        TaskList.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return The ArrayList of tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {

        return tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t The task to be added.
     */
    public String addTasks(Task t) {
        try {
            if (!detectDuplicates(t)) {
                tasks.add(t);
            } else {
                return "Duplicate task found. Task not added.";
            }
        } catch (NullPointerException e) {
            return "Error: Task is null.";
        }
        return "task added successfully";
    }

    /**
     * @param t task to be checked.
     * @return whether t is a duplicate
     */
    public boolean detectDuplicates(Task t) {
        for (Task task : tasks) {
            if (task.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Marks a task as done in the TaskList.
     *
     * @param index The index of the task to be marked as done.
     * @param ui    The Ui object for displaying messages.
     */
    public String markTasks(int index, Ui ui) {
        try {
            tasks.get(index - 1).setStatus();
            return ui.markedMessage(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! The task number is out of bounds. Please provide a valid task number.");
        }
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param in The index of the task to be removed.
     * @param ui The Ui object for displaying messages.
     */
    public String removeTasks(int in, Ui ui) {
        try {
            Task temp = tasks.get(in - 1);
            tasks.remove(in - 1);
            return ui.deleteMessage(temp.toString());
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! The task number is out of bounds. Please provide a valid task number.");
        }
    }

    /**
     * Lists all tasks in the TaskList.
     *
     * @param ui The Ui object for displaying messages.
     */
    public String listTasks(Ui ui) {
        StringBuilder res = new StringBuilder();
        if (tasks.isEmpty()) {
            res.append(ui.showMessage("There are no tasks in the list."));
        } else {
            res.append(ui.showMessage("Here are the tasks in your list:"));
            for (int i = 0; i < tasks.size(); i++) {
                res.append("\n"); // Add a newline character
                res.append(ui.showMessage((i + 1) + ". " + tasks.get(i).toString()));
            }
        }
        return res.toString();
    }


    /**
     * Searches for tasks containing a specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that contain the specified keyword.
     */
    public static ArrayList<Task> keywordSearch(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        return matchedTasks;
    }

    /**
     * @param keyword to be found
     * @param ui display on ui
     * @return String of all tasks found
     */
    public static String findTasks(String keyword, Ui ui) {
        ArrayList<Task> matchingTasks = keywordSearch(keyword);
        StringBuilder res;
        res = new StringBuilder(ui.showMessage("Here are the matching tasks in your list:"));
        for (int i = 0; i < matchingTasks.size(); i++) {
            res.append(ui.showMessage((i + 1) + "." + matchingTasks.get(i).toString()));
        }
        return res.toString();
    }
}
