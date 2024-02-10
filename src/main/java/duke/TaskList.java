package duke;

import java.util.ArrayList;

import duke.task.Task;



/**
 * TaskList class represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return The ArrayList of tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {

        return this.tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t The task to be added.
     */
    public void addTasks(Task t) {

        tasks.add(t);
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
    public ArrayList<Task> keywordSearch(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        return matchedTasks;
    }
}
