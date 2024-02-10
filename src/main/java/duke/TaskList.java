package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;

/**
 * The list of tasks in the program.
 */
public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;

    /**
     * The constructor for TaskList. It initializes as empty.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor with an already defined array of tasks.
     */
    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task must not be null";
        tasks.add(task);
    }

    /**
     * Gets the tasks at the given index.
     *
     * @return the requested task
     * @throws TaskNotFound if the task with the given index doesn't exist
     */
    public Task getTask(int index) throws TaskNotFound {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound(index, e);
        }
    }

    /**
     * Deletes the task at the given index.
     *
     * @throws TaskNotFound if the task with the given index doesn't exist
     */
    public void deleteTask(int index) throws TaskNotFound {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound(index, e);
        }
    }

    /**
     * Finds all tasks containing the given query string.
     */
    public TaskList find(String query) {
        var filteredTasks = tasks.stream()
                .filter(task -> task.inDescription(query))
                .collect(Collectors.toList());
        return new TaskList(new ArrayList<>(filteredTasks));
    }

    @Override
    public String toString() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i) + "\n")
                .collect(Collectors.joining()).stripTrailing();
    }

    /**
     * An exception used to signal that a task from a given request doesn't exist.
     */
    public static class TaskNotFound extends Exception {
        public TaskNotFound(int index, Throwable cause) {
            super("Could not find task " + (index + 1), cause);
        }
    }
}
