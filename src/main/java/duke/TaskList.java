package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.ArrayList;

/**
 * TaskList class represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

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
    public void markTasks(int index, Ui ui) {
        try {
            tasks.get(index - 1).setStatus();
            ui.markedMessage(tasks.get(index - 1));
        }  catch (IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! The task number is out of bounds. Please provide a valid task number.");
        }
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param in The index of the task to be removed.
     * @param ui    The Ui object for displaying messages.
     */
    public void removeTasks(int in, Ui ui) {
        try {
            Task temp = tasks.get(in - 1);
            tasks.remove(in - 1);
            ui.deleteMessage(temp.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! The task number is out of bounds. Please provide a valid task number.");
        }
    }

    /**
     * Lists all tasks in the TaskList.
     *
     * @param ui The Ui object for displaying messages.
     */
    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("There are no tasks in the list.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.get(i).toString());
            }
        }
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
