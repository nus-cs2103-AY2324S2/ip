package duke;

import java.util.ArrayList;

import duke.exceptions.InvalidMarkException;
import duke.tasks.Task;

/**
 * The TaskList class represents a list of tasks.
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
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list the list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t the task to be added
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at the specified index in the TaskList.
     *
     * @param i the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes and returns the task at the specified index in the TaskList.
     * Updates all tasks, since a DoAfter Task could have its beforeTask removed.
     *
     * @param i the index of the task to remove
     * @return the task that was removed
     */
    public Task remove(int i) {
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        this.updateTasks();
        return t;
    }

    /**
     * Returns the index of the specified task in the TaskList.
     *
     * @param task the task that the index should be found
     * @return the index of the task
     */
    public int getIndex(Task task) {
        return this.tasks.indexOf(task);
    }

    /**
     * Generates a formatted string listing all tasks in the TaskList.
     *
     * @return a string representation of the TaskList
     */
    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "There are no tasks currently :)";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); ++i) {
            Task t = this.tasks.get(i);
            String taskString = "\n" + String.valueOf(i + 1) + ". " + t.toString();
            response.append(taskString);
        }
        return response.toString();
    }

    /**
     * Updates all tasks with their dependencies.
     */
    public void updateTasks() {
        for (Task task: this.tasks) {
            task.update(this);
        }
    }

    /**
     * Marks the task at the specified index in the TaskList as done.
     *
     * @param taskNum the index of the task to mark as done
     * @return the task that was marked as done
     */
    public Task markTask(int taskNum) throws InvalidMarkException {
        Task t = this.tasks.get(taskNum);
        t.markAsDone();
        return t;
    }

    /**
     * Unmarks the task at the specified index in the TaskList.
     *
     * @param taskNum the index of the task to unmark
     * @return the task that was unmarked
     */
    public Task unmarkTask(int taskNum) {
        Task t = this.tasks.get(taskNum);
        t.unmark();
        return t;
    }

    /**
     * Checks if the task list contains a specific task.
     *
     * @param task the task to be checked for presence in the task list
     * @return true, if the task list contains the specified task, false otherwise
     */
    public boolean containsTask(Task task) {
        if (this.tasks.contains(task)) {
            return true;
        }
        return false;
    }

    private ArrayList<Task> find(String keyword) {
        ArrayList<Task> match = new ArrayList<>();
        for (Task t: this.tasks) {
            if (t.containsKeyword(keyword)) {
                match.add(t);
            }
        }
        return match;
    }

    /**
     * Returns a string representation of the list of tasks matching the keyword.
     *
     * @return a string representation of list of tasks matching the keyword
     */
    public String findToString(String keyword) {
        ArrayList<Task> match = this.find(keyword);
        if (match.isEmpty()) {
            return "There are no matching tasks! :(";
        }
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < match.size(); ++i) {
            Task t = match.get(i);
            String taskString = "\n" + String.valueOf(i + 1) + ". " + t.toString();
            response.append(taskString);
        }
        return response.toString();
    }

    /**
     * Returns a string representation of the TaskList for storage.
     *
     * @return a string representation of the TaskList for storage
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Task t: this.tasks) {
            s.append(t.save(this));
            s.append("\n");
        }
        return s.toString();
    }
}
