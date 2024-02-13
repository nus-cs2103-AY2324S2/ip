package utilities;

import exceptions.YpxmmException;

import tasks.Task;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /** The list of tasks. */
    public ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param tasks the list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list based on the input command.
     *
     * @param input the input command
     * @param task the task to add
     * @throws YpxmmException if an error occurs while adding the task
     */
    public void addTask(ArrayList<String> input, Task task) throws YpxmmException {
        try {
            if (input.get(0).equals("todo")) {
                tasks.add(task);
            } else {
                try {
                    tasks.add(task);
                } catch (DateTimeParseException e) {
                    throw new YpxmmException("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
                }
            }
        } catch (DateTimeParseException e) {
            throw new YpxmmException("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index the index of the task to delete
     * @throws YpxmmException if an error occurs while deleting the task
     */
    public void deleteTask(int index) throws YpxmmException {
        try {
            try {
                tasks.remove(index - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.isEmpty() ? "no tasks to delete." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new YpxmmException("Eh u seh isit? Now your list got " +
                    (tasks.isEmpty() ? "no tasks to delete." : tasks.size() +
                            " tasks, enter any number from 1 to " + tasks.size()));
        }
    }

    public ArrayList<Task> findTask(String string) {
        ArrayList<Task> ans = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getName().contains(string)) {
                ans.add(t);
            }
        }
        return ans;
    }
}
