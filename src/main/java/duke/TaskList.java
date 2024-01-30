package duke;

import duke.tasks.Task;

import java.util.ArrayList;

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
     *
     * @param i the index of the task to remove
     * @return the task that was removed
     */
    public Task remove(int i) {
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        return t;
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
            String taskString = "\n" + String.valueOf(i+1) + ". " + t.toString();
            response.append(taskString);
        }
        response.append("\n");
        response.append(TextTemplate.LINE_BREAK);
        return response.toString();
    }

    /**
     * Marks the task at the specified index in the TaskList as done.
     *
     * @param taskNum the index of the task to mark as done
     * @return the task that was marked as done
     */
    public Task markTask(int taskNum) {
        Task t = this.tasks.get(taskNum);
        t.maskAsDone();
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
     * Returns a string representation of the TaskList for storage.
     *
     * @return a string representation of the TaskList for storage
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Task t: this.tasks) {
            s.append(t.save());
            s.append("\n");
        }
        return s.toString();
    }
}
