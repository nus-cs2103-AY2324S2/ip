package felix.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import felix.task.Task;

/**
 * Class representing a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Private constructor for internal usage.
     */
    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task from taskList.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns task at index i from taskList.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Returns number of tasks in taskList.
     */
    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Deletes task at index i from taskList.
     */
    public void deleteTask(int i) {
        this.tasks.remove(i);
    }

    /**
     * Returns the String representation of all tasks within taskList to be written to a file.
     */
    public String getFileRepresentation() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(this.tasks.get(i).toFileString());
            if (i < this.tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Returns new task list only containing the tasks with the specified keyword as a substring of description.
     * @param keyword String to filter tasks with.
     * @return list of tasks with description containing specified keyword.
     */
    public TaskList filterByKeyword(String keyword) {
        return new TaskList(this.tasks.stream()
                .filter(task -> task.hasKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Returns String representation of all tasks within taskList to be displayed.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("%d. %s", i + 1, this.tasks.get(i)));
            if (i < this.tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
