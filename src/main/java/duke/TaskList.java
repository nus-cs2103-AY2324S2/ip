package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();


    /**
     * Constructor for TaskList
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList.addAll(taskList);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The length of the tasks list of tasks.
     */
    public int getLength() {
        return this.taskList.size();
    }


    /**
     * Checks if the list of tasks is empty.
     *
     * @return True if the list is empty, else False.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }


    /**
     * Adds a task to the list of tasks
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }


    /**
     * Removes a task from the list of tasks.
     *
     * @param taskId Task ID number of task to be removed.
     */
    public void remove(int taskId) {
        this.taskList.remove(taskId - 1);
    }


    /**
     * Retrieves the task with the corresponding task ID from the list o tasks.
     *
     * @param taskId Task ID number of task to be retrieved.
     * @return The Task with the corresponding task ID.
     */
    public Task get(int taskId) {
        return this.taskList.get(taskId - 1);
    }


    /**
     * Filters the list of tasks by the keyword.
     *
     * @param keyword Keyword that is searched.
     * @return Filtered list of tasks.
     */
    public TaskList filter(String keyword) {
        List<Task> filteredTasks = this.taskList.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<>(filteredTasks));
    }
}
