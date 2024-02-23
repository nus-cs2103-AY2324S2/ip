package TaskFlow.task;

import TaskFlow.exception.DukeException;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks in the Duke chatbot application.
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
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a task to the task list and also handle duplicate tasks.
     *
     * @param task The task to be added.
     * @throws DukeException throws an error when there is a same task
     *                       being added into the list.
     */
    public void add(Task task) throws DukeException {
        for (int i = 0; i < getTaskSize(); i++) {
            if (tasks.get(i).equals(task)) {
                throw new DukeException("Task with same details already exists.\n"
                + "Please try again.\n");
            } else {
                tasks.add(task);
            }
        }
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Lists all tasks in the task list.
     */
    public String list() {
        String lists = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString() + "\n")
                .collect(Collectors.joining());
        return lists;
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void mark(int index) {
        tasks.get(index - 1).markAsDone();
    }

    /**
     * Marks a task as undone based on its index.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void unmark(int index) {
        tasks.get(index - 1).markAsUndone();
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the description of a task based on its index.
     *
     * @param index The index of the task.
     * @return The description of the task.
     */
    public String getTaskDescription(int index) {
        return this.tasks.get(index).description;
    }

    /**
     * Finds tasks in the list that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks containing the specified keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTask = tasks.stream()
                .filter(task -> task.containsKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return matchingTask;
    }
}
