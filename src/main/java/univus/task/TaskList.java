package univus.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Univus application.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new instance of the TaskList class with an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes and returns the task at the specified index from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed from the list.
     */
    public Task remove(int index) {
        return this.taskList.remove(index - 1);
    }

    /**
     * Retrieves and returns the task at the specified index from the task list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the entire task list.
     *
     * @return An ArrayList containing all the tasks in the list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }


    /**
     * @param keyword The keyword to find the sentence containing this keyword.
     * @return The sentences contain this keyword.
     */
    public TaskList find(String keyword) {
        TaskList searchingResult = new TaskList();
        for (Task task: taskList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                searchingResult.add(task);
            }
        }
        return searchingResult;
    }

    /**
     * Lists and prints all tasks in the task list with their respective indices.
     */
    public void list() {
        int index = 1;
        for (Task msg : taskList) {
            System.out.println(index + "." + msg.toString());
            index++;
        }
    }
}
