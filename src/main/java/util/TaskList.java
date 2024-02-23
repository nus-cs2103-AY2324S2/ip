package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList object.
     *
     * @throws IOException if there is an error initializing the storage.
     */
    public TaskList() throws IOException {
        TaskList.tasks = new ArrayList<>();
        this.storage = new Storage(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to be added.
     * @throws IOException if there is an error writing to the storage.
     */
    public void add(Task t) throws IOException {
        TaskList.tasks.add(t);
        storage.writeToFile(tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param i the index of the task to be deleted.
     * @return the deleted task.
     * @throws IOException if there is an error writing to the storage.
     */
    public Task delete(int i) throws IOException {
        Task t = TaskList.tasks.get(i - 1);
        TaskList.tasks.remove(i - 1);
        storage.writeToFile(tasks);
        return t;
    }

    /**
     * Gets the size of the task list.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return TaskList.tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the task to be marked.
     * @return the marked task.
     * @throws IOException if there is an error writing to the storage.
     */
    public Task mark(int i) throws IOException {
        Task t = TaskList.tasks.get(i - 1).mark();
        storage.writeToFile(tasks);
        return t;
    }

    /**
     * Marks a task as undone.
     *
     * @param i the index of the task to be unmarked.
     * @return the unmarked task.
     * @throws IOException if there is an error writing to the storage.
     */
    public Task unmark(int i) throws IOException {
        Task t = TaskList.tasks.get(i - 1).unmark();
        storage.writeToFile(tasks);
        return t;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds tasks in the task list that contain all the specified strings.
     *
     * @param s the strings to search for in the tasks
     * @return a list of tasks that contain all the specified strings
     */
    public static List<Task> find(String... s) {
        return tasks.stream()
                .filter(t -> Arrays.stream(s).allMatch(t::contains))
                .collect(Collectors.toList());
    }

    /**
     * Sorts the tasks in the task list based on their date and time.
     */
    public static void sort() {
        tasks.sort(Comparator.naturalOrder());
    }

    /**
     * Returns the size of the task list.
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            sb.append(i + 1).append(".").append(TaskList.tasks.get(i).toString());
            if (i != TaskList.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * Checks if the task list contains a specific task.
     *
     * @param t the task to check for.
     * @return true if the task list contains the task, false otherwise.
     */
    public boolean contains(Task t) {
        return tasks.contains(t);
    }
}
