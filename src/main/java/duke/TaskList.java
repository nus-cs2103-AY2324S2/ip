package duke;

import java.io.Serializable;
import java.util.ArrayList;

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
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task must not be null";
        tasks.add(task);
    }

    /**
     * Gets the tasks at the given index.
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
        TaskList tasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.inDescription(query)) {
                tasks.addTask(task);
            }
        }
        return tasks;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        s.append("1").append(". ").append(tasks.get(0));
        for (int i = 1; i < tasks.size(); i++) {
            s.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return s.toString();
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
